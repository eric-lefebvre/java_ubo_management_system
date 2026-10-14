package ui;

import formation.AnneeFormation;
import formation.ConnexionImpossibleException;
import formation.GestionEtudiant;
import formation.InformationPersonnelle;
import formation.NonConnecteException;
import formation.UeOptionnelle;
import formation.UniteEnseignement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Le contr�leur associ� � la fen�tre d�finie dans etudiants.fxml.
 *
 * @author Eric Cariou
 */
public class EtudiantsControleur {
  
  @FXML
  private ResourceBundle resources;
  
  @FXML
  private URL location;
  
  @FXML
  private CheckBox checkInscriptionFinalisee;
  
  @FXML
  private TextField entreeAdresseEtudiant;
  
  @FXML
  private TextField entreeAgeEtudiant;
  
  @FXML
  private TextField entreeGroupeTD;
  
  @FXML
  private TextField entreeGroupeTP;
  
  @FXML
  private TextField entreeMotDePasseEtudiant;
  
  @FXML
  private TextField entreeNomEtudiant;
  
  @FXML
  private TextField entreeNombreOptions;
  
  @FXML
  private TextField entreeNumeroEtudiant;
  
  @FXML
  private TextField entreePrenomEtudiant;
  
  @FXML
  private ListView<String> listeMessagesNonLus;
  
  @FXML
  private ListView<String> listeTousMessages;
  
  @FXML
  private ListView<String> listeUEOptionnellesFormation;
  
  @FXML
  private ListView<String> listeUESuiviesEtudiant;
  
  @FXML
  private TextArea zoneTexteContenuMessage;
  
  private GestionEtudiant gestion = new GestionEtudiant();
  
  @FXML
  void actionBoutonChoisirOption(ActionEvent event) {
    
    if (AnneeFormation.instance == null) {
      afficherMessageErreur("Annee de formation non cree");
      return;
    }
    
    if (checkInscriptionFinalisee.isSelected()) {
      afficherMessage("Inscription deja finalisee");
      return;
    }
    
    String nomUe =
        listeUEOptionnellesFormation.getSelectionModel().getSelectedItem();
    UeOptionnelle ueOpt = null;
    
    if (nomUe == null || nomUe == "") {
      afficherMessageErreur("Aucune ue selectionnee");
      return;
    }
    
    for (UeOptionnelle ue : AnneeFormation.instance.getListeUeOption()) {
      if (nomUe.equals(ue.getNom())) {
        ueOpt = ue;
      }
    }
    
    if (ueOpt == null) {
      return;
    }
    
    try {
      if (gestion.choisirOption(ueOpt)) {
        refreshWindow();
      } else {
        afficherMessageErreur("Vous ne pouvez pas selectionner cette ue");
      }
    } catch (NonConnecteException e) {
      afficherMessageErreur("Vous n'etes pas connecte");
      return;
    }
  }
  
  @FXML
  void actionBoutonConnexion(ActionEvent event) {
    
    if (AnneeFormation.instance == null) {
      afficherMessageErreur("Annee de formation non cree");
      return;
    }
    
    boolean succes = false;
    int numEtudiant;
    
    try {
      numEtudiant = Integer.valueOf(entreeNumeroEtudiant.getText());
    } catch (NumberFormatException e) {
      afficherMessageErreur("Nombre invalide");
      return;
    }
    
    try {
      succes =
          gestion.connexion(numEtudiant, entreeMotDePasseEtudiant.getText());
    } catch (ConnexionImpossibleException e) {
      afficherMessageErreur("Connexion deja etablie");
      return;
    }
    
    if (!succes) {
      afficherMessageErreur("Echec connexion");
    }
    
    refreshWindow();
  }
  
  void refreshWindow() {
    
    try {
      // TD
      entreeGroupeTD
          .setText(Integer.toString(gestion.getNumeroGroupeTravauxDiriges()));
      // TP
      entreeGroupeTP
          .setText(Integer.toString(gestion.getNumeroGroupeTravauxPratiques()));
      // Options
      entreeNombreOptions.setText(Integer.toString(gestion.nombreOptions()));
      // Liste UE suivies
      listeUESuiviesEtudiant.getItems().clear();
      ObservableList<String> listSuivies = listeUESuiviesEtudiant.getItems();
      for (UniteEnseignement e : gestion.enseignementsSuivis()) {
        listSuivies.add(e.getNom());
      }
      listeUESuiviesEtudiant.setItems(listSuivies);
      // Liste UE formation
      listeUEOptionnellesFormation.getItems().clear();
      ObservableList<String> listOpt = listeUEOptionnellesFormation.getItems();
      for (UniteEnseignement e : gestion.enseignementsOptionnels()) {
        listOpt.add(e.getNom());
      }
      listeUEOptionnellesFormation.setItems(listOpt);
      // Checkbox inscription finalisee
      checkInscriptionFinalisee.setSelected(gestion.inscriptionFinalisee());
      
    } catch (NonConnecteException e) {
      // TD
      entreeGroupeTD.setText("");
      // TP
      entreeGroupeTP.setText("");
      // Options
      entreeNombreOptions.setText("");
      // Liste UE suivies
      listeUESuiviesEtudiant.getItems().clear();
      ObservableList<String> listSuivies = listeUESuiviesEtudiant.getItems();
      listeUESuiviesEtudiant.setItems(listSuivies);
      // Liste UE formation
      listeUEOptionnellesFormation.getItems().clear();
      ObservableList<String> listOpt = listeUEOptionnellesFormation.getItems();
      listeUEOptionnellesFormation.setItems(listOpt);
      // Checkbox inscription finalisee
      checkInscriptionFinalisee.setSelected(false);
      return;
    }
  }
  
  @FXML
  void actionBoutonDeconnexion(ActionEvent event) {
    
    if (AnneeFormation.instance == null) {
      afficherMessageErreur("Annee de formation non cree");
      return;
    }
    
    try {
      gestion.deconnexion();
      
      refreshWindow();
      
      afficherMessage("Deconnexion reussie");
    } catch (NonConnecteException e) {
      return;
    }
  }
  
  @FXML
  void actionBoutonInscription(ActionEvent event) {
    
    if (AnneeFormation.instance == null) {
      afficherMessageErreur("Annee de formation non cree");
      return;
    }
    
    int age;
    
    try {
      age = Integer.valueOf(entreeAgeEtudiant.getText());
    } catch (NumberFormatException e) {
      age = 0;
    }
    
    int numEtu = gestion
        .inscription(new InformationPersonnelle(entreeNomEtudiant.getText(),
            entreePrenomEtudiant.getText(), entreeAdresseEtudiant.getText(),
            age), entreeMotDePasseEtudiant.getText());
    
    if (numEtu != -1) {
      afficherMessage("Inscription réussie : NumeroEtudiant = " + numEtu);
      
    } else {
      afficherMessageErreur("Echec de l'inscription, mot de passe trop court");
    }
  }
  
  @FXML
  void actionBoutonRafraichirListesMessages(ActionEvent event) {
    
    if (AnneeFormation.instance == null) {
      afficherMessageErreur("Annee de formation non cree");
      return;
    }
    
    listeTousMessages.getItems().clear();
    ObservableList<String> listMsg = listeTousMessages.getItems();
    try {
      listMsg.addAll(gestion.listeTousMessages());
    } catch (NonConnecteException e) {
      afficherMessageErreur("Vous n'etes pas connecte");
      return;
    }
    listeTousMessages.setItems(listMsg);
    
    listeMessagesNonLus.getItems().clear();
    ObservableList<String> listNonLus = listeMessagesNonLus.getItems();
    try {
      listNonLus.addAll(gestion.listeMessageNonLus());
    } catch (NonConnecteException e) {
      afficherMessageErreur("Vous n'etes pas connecte");
      return;
    }
    listeMessagesNonLus.setItems(listNonLus);
  }
  
  @FXML
  void actionSelectionMessageListeMessagesNonLus(MouseEvent event) {
    String msg = this.listeMessagesNonLus.getSelectionModel().getSelectedItem();
    
    if (msg != null) {
      this.zoneTexteContenuMessage.setText(msg);
      try {
        gestion.lireMessage(msg);
      } catch (NonConnecteException e) {
        afficherMessageErreur("Vous n'etes pas connecte");
      }
    }
  }
  
  @FXML
  void actionSelectionMessageListeTousMessages(MouseEvent event) {
    String msg = this.listeTousMessages.getSelectionModel().getSelectedItem();
    
    if (msg != null) {
      this.zoneTexteContenuMessage.setText(msg);
    }
  }
  
  private void afficherMessageErreur(String message) {
    
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText(message);
    
    Label label = (Label) alert.getDialogPane().lookup(".content.label");
    label.setStyle("-fx-font-size: 12px;");
    
    alert.showAndWait();
  }
  
  private void afficherMessage(String message) {
    
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setHeaderText(null);
    alert.setContentText(message);
    
    Label label = (Label) alert.getDialogPane().lookup(".content.label");
    label.setStyle("-fx-font-size: 12px;");
    
    alert.showAndWait();
  }
  
  @FXML
  void initialize() {}
  
}
