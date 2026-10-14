package ui;

import formation.AnneeFormation;
import formation.Etudiant;
import formation.GestionEtudiant;
import formation.GestionFormation;
import formation.InformationPersonnelle;
import formation.Responsable;
import formation.UeObligatoire;
import formation.UeOptionnelle;
import formation.UniteEnseignement;
import io.GestionSauvegarde;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * Le contr�leur associ� � la fen�tre d�finie dans formation.fxml.
 *
 * @author Eric Cariou
 */
public class FormationControleur {
  
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
  private TextField entreeCapaciteAccueil;
  
  @FXML
  private TextField entreeEmailResponsableFormation;
  
  @FXML
  private TextField entreeGroupeTDEtudiant;
  
  @FXML
  private TextField entreeGroupeTPEtudiant;
  
  @FXML
  private TextField entreeNomEtudiant;
  
  @FXML
  private TextField entreeNomFormation;
  
  @FXML
  private TextField entreeNomResponsableFormation;
  
  @FXML
  private TextField entreeNomResponsableUE;
  
  @FXML
  private TextField entreeNomUE;
  
  @FXML
  private TextField entreeNombreChoixOptions;
  
  @FXML
  private TextField entreePrenomEtudiant;
  
  @FXML
  private TextField entreeTailleGroupeTD;
  
  @FXML
  private TextField entreeTailleGroupeTP;
  
  @FXML
  private Label labelListeEtudiants;
  
  @FXML
  private Label labelNbGroupesTD;
  
  @FXML
  private Label labelNbGroupesTP;
  
  @FXML
  private ListView<Etudiant> listeEtudiants;
  
  @FXML
  private ListView<UniteEnseignement> listeUEObligatoires;
  
  @FXML
  private ListView<UniteEnseignement> listeUEOptionnelles;
  
  @FXML
  private ToggleGroup obligation;
  
  @FXML
  private RadioButton radioBoutonObligatoire;
  
  @FXML
  private RadioButton radioBoutonOptionnelle;
  
  private GestionFormation gestion = new GestionFormation();
  
  @FXML
  void actionBoutonAffectationAutomatique(ActionEvent event) {
    
    if (AnneeFormation.instance == null) {
      afficherMessageErreur("Annee de formation non cree");
      return;
    }
    
    if (AnneeFormation.instance.getTailleMaxTd() == -1
        || AnneeFormation.instance.getTailleMaxTp() == -1) {
      afficherMessageErreur("Il faut definir une taille max des groupes");
      return;
    }
    
    gestion.attribuerAutomatiquementGroupes();
    
    afficherMessage("Groupes attribues avec succes");
    
    // Mise a jour label nombre groupes td et tp
    labelNbGroupesTD.setText(
        Integer.toString(AnneeFormation.instance.getListeGroupeTd().size()));
    labelNbGroupesTP.setText(
        Integer.toString(AnneeFormation.instance.getListeGroupeTd().size()));
    
    LinkedHashSet<Etudiant> listeEtudiant =
        gestion.getAnneeFormation().getListeEtudiant();
    
    if (listeEtudiant == null) {
      return;
    }
    
    listeEtudiants.getItems().clear();
    ObservableList<Etudiant> uiListEtudiant = listeEtudiants.getItems();
    uiListEtudiant.addAll(listeEtudiant);
    listeEtudiants.setItems(uiListEtudiant);
  }
  
  @FXML
  void actionBoutonAffectationManuelleGroupes(ActionEvent event) {
    
    if (AnneeFormation.instance == null) {
      afficherMessageErreur("Annee de formation non cree");
      return;
    }
    
    int numeroTp;
    int numeroTd;
    
    try {
      numeroTp = Integer.valueOf(entreeGroupeTPEtudiant.getText());
      numeroTd = Integer.valueOf(entreeGroupeTDEtudiant.getText());
      
      if (numeroTp < 1
          || numeroTp > AnneeFormation.instance.getListeGroupeTp().size()) {
        afficherMessageErreur("Numero de groupe de tp invalide");
        return;
        
      } else if (numeroTd < 1
          || numeroTd > AnneeFormation.instance.getListeGroupeTd().size()) {
        afficherMessageErreur("Numero de groupe de td invalide");
        return;
      }
      
    } catch (NumberFormatException e) {
      afficherMessageErreur("Numero invalide");
      return;
    }
    
    
    Etudiant etu = listeEtudiants.getSelectionModel().getSelectedItem();
    if (etu != null) {
      int res = gestion.changerGroupe2(etu, numeroTd, numeroTp);
      if (res == -3) {
        afficherMessageErreur("Erreur ajout Td et Tp");
      } else if (res == -2) {
        afficherMessageErreur("Erreur ajout Tp");
      } else if (res == -1) {
        afficherMessageErreur("Erreur ajout Td");
      } else if (res == 0) {
        afficherMessage("Changement manuel reussi");     
      }
    }
    
    LinkedHashSet<Etudiant> listeEtudiant =
        gestion.getAnneeFormation().getListeEtudiant();
    
    if (listeEtudiant == null) {
      return;
    }
    
    listeEtudiants.getItems().clear();
    ObservableList<Etudiant> uiListEtudiant = listeEtudiants.getItems();
    uiListEtudiant.addAll(listeEtudiant);
    listeEtudiants.setItems(uiListEtudiant);
  }
  
  @FXML
  void actionBoutonAfficherEtudiantsGroupeTD(ActionEvent event) {
    
    if (AnneeFormation.instance == null) {
      afficherMessageErreur("Annee de formation non cree");
      return;
    }
    
    int numeroGroupe;
    
    try {
      numeroGroupe = Integer.valueOf(entreeGroupeTDEtudiant.getText());
      
    } catch (NumberFormatException e) {
      afficherMessageErreur("Nombre invalide");
      return;
    }
    
    if (numeroGroupe < 1
        || numeroGroupe > AnneeFormation.instance.getListeGroupeTd().size()) {
      afficherMessageErreur("Numero de groupe de td invalide");
      return;
    }
    
    Set<Etudiant> listeEtudiant = gestion.getAnneeFormation()
        .getGroupeTd(numeroGroupe).getListeEtudiant();
    
    if (listeEtudiant != null) {
      listeEtudiants.getItems().clear();
      ObservableList<Etudiant> uiListEtudiant = listeEtudiants.getItems();
      uiListEtudiant.addAll(listeEtudiant);
      listeEtudiants.setItems(uiListEtudiant);
    }
  }
  
  @FXML
  void actionBoutonAfficherEtudiantsGroupeTP(ActionEvent event) {
    
    if (AnneeFormation.instance == null) {
      afficherMessageErreur("Annee de formation non cree");
      return;
    }
    
    int numeroGroupe;
    
    try {
      numeroGroupe = Integer.valueOf(entreeGroupeTPEtudiant.getText());
    } catch (NumberFormatException e) {
      afficherMessageErreur("Nombre invalide");
      return;
    }
    
    if (numeroGroupe < 1
        || numeroGroupe > AnneeFormation.instance.getListeGroupeTp().size()) {
      afficherMessageErreur("Numero de groupe de tp invalide");
      return;
    }
    
    Set<Etudiant> listeEtudiant =
        gestion.listeEtudiantsGroupePratique(numeroGroupe);
    
    if (listeEtudiant != null) {
      listeEtudiants.getItems().clear();
      ObservableList<Etudiant> uiListEtudiant = listeEtudiants.getItems();
      uiListEtudiant.addAll(listeEtudiant);
      listeEtudiants.setItems(uiListEtudiant);
    }
  }
  
  @FXML
  void actionBoutonAfficherEtudiantsUEOptionnelle(ActionEvent event) {
    
    if (AnneeFormation.instance == null) {
      afficherMessageErreur("Annee de formation non cree");
      return;
    }
    
    UniteEnseignement ue =
        listeUEOptionnelles.getSelectionModel().getSelectedItem();
    
    if (ue == null) {
      afficherMessageErreur("Aucune ue selectionnee");
      return;
    }
    
    listeEtudiants.getItems().clear();
    ObservableList<Etudiant> uiListEtudiant = listeEtudiants.getItems();
    for (Etudiant e : ue.getListeEtudiants()) {
      uiListEtudiant.add(e);
    }
    listeEtudiants.setItems(uiListEtudiant);
  }
  
  @FXML
  void actionBoutonAfficherTousEtudiants(ActionEvent event) {
    
    if (AnneeFormation.instance == null) {
      afficherMessageErreur("Annee de formation non cree");
      return;
    }
    
    LinkedHashSet<Etudiant> listeEtudiant =
        gestion.getAnneeFormation().getListeEtudiant();
    
    if (listeEtudiant == null) {
      return;
    }
    
    listeEtudiants.getItems().clear();
    ObservableList<Etudiant> uiListEtudiant = listeEtudiants.getItems();
    uiListEtudiant.addAll(listeEtudiant);
    listeEtudiants.setItems(uiListEtudiant);
  }
  
  @FXML
  void actionBoutonCreerFormation(ActionEvent event) {
    gestion.creerFormation(entreeNomFormation.getText(),
        entreeNomResponsableFormation.getText(),
        entreeEmailResponsableFormation.getText());
    
    afficherMessage("Création réussie : Responsable: "
        + AnneeFormation.instance.getResponsable());
  }
  
  @FXML
  void actionBoutonCreerNouvelleUE(ActionEvent event) {
    
    if (AnneeFormation.instance == null) {
      afficherMessageErreur("Annee de formation non cree");
      return;
    }
    
    if (radioBoutonOptionnelle.isSelected()) {
      int capaciteAccueil;
      
      try {
        capaciteAccueil = Integer.valueOf(entreeCapaciteAccueil.getText());
        
      } catch (NumberFormatException e) {
        afficherMessageErreur("Nombre invalide");
        return;
      }
      
      gestion.ajouterEnseignementOptionnel(
          new UeOptionnelle(new Responsable(entreeNomResponsableUE.getText()),
              entreeNomUE.getText(), capaciteAccueil),
          capaciteAccueil);
      
      refreshListeUeOptionnelles();
    }
    
    if (radioBoutonObligatoire.isSelected()) {
      gestion.ajouterEnseignementObligatoire(
          new UeObligatoire(new Responsable(entreeNomResponsableUE.getText()),
              entreeNomUE.getText()));
      
      refreshListeUeObligatoires();
    }
  }
  
  void refreshListeUeOptionnelles() {
    
    listeUEOptionnelles.getItems().clear();
    ObservableList<UniteEnseignement> listOpt = listeUEOptionnelles.getItems();
    for (UniteEnseignement e : AnneeFormation.instance.getListeUeOption()) {
      listOpt.add(e);
    }
    listeUEOptionnelles.setItems(listOpt);
  }
  
  void refreshListeUeObligatoires() {
    
    listeUEObligatoires.getItems().clear();
    ObservableList<UniteEnseignement> listObl = listeUEObligatoires.getItems();
    for (UniteEnseignement e : AnneeFormation.instance
        .getListeUeObligatoire()) {
      listObl.add(e);
    }
    listeUEObligatoires.setItems(listObl);
  }
  
  @FXML
  void actionBoutonNombreChoixOptions(ActionEvent event) {
    
    if (AnneeFormation.instance == null) {
      afficherMessageErreur("Annee de formation non cree");
      return;
    }
    
    int nombreOptions;
    
    try {
      nombreOptions = Integer.valueOf(entreeNombreChoixOptions.getText());
    } catch (NumberFormatException e) {
      afficherMessageErreur("Nombre invalide");
      return;
    }
    
    gestion.definirNombreOptions(nombreOptions);
  }
  
  @FXML
  void actionBoutonSetTailleGroupeTD(ActionEvent event) {
    
    if (AnneeFormation.instance == null) {
      afficherMessageErreur("Annee de formation non cree");
      return;
    }
    
    int tailleTd;
    
    try {
      tailleTd = Integer.valueOf(entreeTailleGroupeTD.getText());
    } catch (NumberFormatException e) {
      afficherMessageErreur("Nombre invalide");
      return;
    }
    
    gestion.setTailleGroupeDirige(tailleTd);
  }
  
  @FXML
  void actionBoutonSetTailleGroupeTP(ActionEvent event) {
    
    if (AnneeFormation.instance == null) {
      afficherMessageErreur("Annee de formation non cree");
      return;
    }
    
    int tailleTp;
    
    try {
      tailleTp = Integer.valueOf(entreeTailleGroupeTP.getText());
    } catch (NumberFormatException e) {
      afficherMessageErreur("Nombre invalide");
      return;
    }
    
    gestion.setTailleGroupePratique(tailleTp);
  }
  
  @FXML
  void actionMenuApropos(ActionEvent event) {
    
  }
  
  @FXML
  void actionMenuCharger(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir un fichier de sauvegarde");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Fichiers de sauvegarde", "*.dat"));
    
    // Afficher la boîte de dialogue pour choisir le fichier
    File selectedFile = fileChooser.showOpenDialog(null);
    
    if (selectedFile != null) {
      try {
        GestionSauvegarde sauv = new GestionSauvegarde();
        
        sauv.chargerDonnees(selectedFile.getAbsolutePath());
        this.entreeNomFormation.setText(AnneeFormation.instance.getNom());
        this.entreeNomResponsableFormation
            .setText(AnneeFormation.instance.getResponsable().getNom());
        this.entreeEmailResponsableFormation
            .setText(AnneeFormation.instance.getResponsable().getEmail());
        this.entreeTailleGroupeTD.setText(
            Integer.toString(AnneeFormation.instance.getTailleMaxTd()));
        this.entreeTailleGroupeTP.setText(
            Integer.toString(AnneeFormation.instance.getTailleMaxTp()));
        this.entreeNombreChoixOptions.setText(
            Integer.toString(AnneeFormation.instance.getNombreChoixOption()));
        refreshListeUeObligatoires();
        refreshListeUeOptionnelles();
        
        afficherMessage("Données chargées avec succes.");
      } catch (IOException e) {
        afficherMessageErreur("Erreur lors du chargement des données.");
        e.printStackTrace();
      }
    } else {
      afficherMessageErreur("Aucun fichier sélectionné.");
    }
  }
  
  
  @FXML
  void actionMenuQuitter(ActionEvent event) {
    
  }
  
  @FXML
  void actionMenuSauvegarder(ActionEvent event) {
    
    GestionSauvegarde sauv = new GestionSauvegarde();
    try {
      sauv.sauvegarderDonnees("sauvegarde.dat");
      afficherMessage("Données sauvegardées avec succès.");
    } catch (NullPointerException e) {
      afficherMessage("Il n'y a pas encore de données à sauvegarder.");
    } catch (IOException e) {
      afficherMessageErreur("Erreur lors de la sauvegarde des données");
      e.printStackTrace();
    }
  }
  
  @FXML
  void actionSelectionEtudiant(MouseEvent event) {
    
    Etudiant e = listeEtudiants.getSelectionModel().getSelectedItem();
    
    if (e != null) {
      InformationPersonnelle infos = e.getInfoPersoEtudiant();
      
      entreeNomEtudiant.setText(infos.getNom());
      entreePrenomEtudiant.setText(infos.getPrenom());
      entreeAdresseEtudiant.setText(infos.getAdresse());
      entreeAgeEtudiant.setText(Integer.toString(infos.getAge()));
      checkInscriptionFinalisee.setSelected(isInscriptionFinalisee(e));
      entreeGroupeTDEtudiant.setText(Integer.toString(e.getGroupeTd()));
      entreeGroupeTPEtudiant.setText(Integer.toString(e.getGroupeTp()));
    }
    
  }
  
  boolean isInscriptionFinalisee(Etudiant e) {
    
    if (e.getUeopt().size() < AnneeFormation.instance.getNombreChoixOption()
        || e.getGroupeTd() == -1 || e.getGroupeTp() == -1) {
      return false;
    }
    return true;
  }
  
  @FXML
  void actionSelectionUEObligatoire(MouseEvent event) {
    
    UniteEnseignement ue =
        listeUEObligatoires.getSelectionModel().getSelectedItem();
    
    if (ue != null) {
      entreeNomUE.setText(ue.getNom());
      entreeNomResponsableUE.setText(ue.getResponsable().getNom());
      entreeCapaciteAccueil.setText("");
    }
  }
  
  @FXML
  void actionSelectionUEOptionnelle(MouseEvent event) {
    
    UniteEnseignement ue =
        listeUEOptionnelles.getSelectionModel().getSelectedItem();
    UeOptionnelle ueOpt = (UeOptionnelle) ue;
    
    if (ueOpt != null) {
      entreeNomUE.setText(ueOpt.getNom());
      entreeNomResponsableUE.setText(ueOpt.getResponsable().getNom());
      entreeCapaciteAccueil.setText(Integer.toString(ueOpt.getMaxEtudiants()));
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
  void initialize() {
    
  }
  
}
