package io;

import java.io.IOException;

/**
 * Definit les methodes permettant de sauvegarder les donnees dans un fichier.
 *
 * @author Eric Cariou
 */
public interface InterSauvegarde {
  
  /**
   * Sauvegarde toutes les donnees de la formation : liste des UEs, des
   * etudiants et des groupes.
   *
   * @param nomFichier le fichier dans lequel sauvegarder les donnees
   * @throws IOException en cas de probleme de sauvegarde
   */
  void sauvegarderDonnees(String nomFichier) throws IOException;
  
  /**
   * Charge les donnees de la formation (UEs, etudiants, groupes) a partir d'un
   * fichier.
   *
   * @param nomFichier le fichier dans lequel les donnees ont ete sauvegardees
   * @throws IOException en cas de probleme de chargement
   */
  void chargerDonnees(String nomFichier) throws IOException;
}
