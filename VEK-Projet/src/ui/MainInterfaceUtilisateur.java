package ui;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Classe qui lance l'interface graphique.
 *
 * @author Eric Cariou
 */
public final class MainInterfaceUtilisateur extends Application {
  /**
   * Affiche la fenetre de gestion des etudiants.
   */
  public void startFenetreEtudiants() {
    try {
      URL url = getClass().getResource("etudiants.fxml");
      FXMLLoader fxmlLoader = new FXMLLoader(url);
      VBox root = (VBox) fxmlLoader.load();
      
      Scene scene = new Scene(root, 920, 500);
      
      Stage stage = new Stage();
      stage.setResizable(true);
      stage.setTitle("Gestion des etudiants");
      
      stage.setScene(scene);
      stage.show();
      
    } catch (IOException e) {
      System.err.println("Erreur au chargement de la fenetre etudiants : " + e);
    }
  }
  
  /**
   * Affiche la fenetre de gestion de formation.
   *
   * @param primaryStage le parametre passe par JavaFX pour la fenetre
   *        principale
   */
  public void startFenetreFormation(Stage primaryStage) {
    try {
      URL url = getClass().getResource("formation.fxml");
      FXMLLoader fxmlLoader = new FXMLLoader(url);
      VBox root = (VBox) fxmlLoader.load();
      
      Scene scene = new Scene(root, 930, 590);
      
      primaryStage.setScene(scene);
      primaryStage.setResizable(true);
      primaryStage.setTitle("Gestion de formation");
      primaryStage.show();
      
    } catch (IOException e) {
      System.err.println("Erreur au chargement de la fenetre formation : " + e);
    }
  }
  
  @Override
  public void start(Stage primaryStage) {
    
    // Lancement des 2 fenetres de l'application
    this.startFenetreFormation(primaryStage);
    this.startFenetreEtudiants();
    
    // RAJOUTER ICI OU AVANT LE CODE POUR INSTANCIER
    // LES CLASSES DE VOTRE CODE
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}
