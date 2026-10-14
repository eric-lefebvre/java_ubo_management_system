package formation;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Iterator;

/**
 * classe groupe.
 *
 * @author Victor Quilgars
 */

public abstract class Groupe implements Serializable {
  
  /**
   * attribut de la sérialisation auto genere.
   */
  private static final long serialVersionUID = 1439681243109114069L;
  
  /**
   * numero du groupe.
   * 
   */
  protected int numero;
  
  /**
   * taille max du groupe.
   */
  protected int tailleMax;
  
  /**
   * Liste de etudiants du groupe.
   */
  protected LinkedHashSet<Etudiant> listeEtudiant;
  
  
  /**
   * annee de fomration dans lequel est le groupe.
   */
  protected AnneeFormation anneeFormation;
  
  /**
   * getter numero du groupe.
   *
   * @return numero du groupe.
   */
  public int getNumero() {
    
    return numero;
    
  }
  
  /**
   * getter tailleMax du groupe.
   *
   * @return tailleMax du groupe.
   */
  public int getTailleMax() {
    
    return tailleMax;
    
  }
  
  /**
   * geter de la liste des etudiants.
   *
   * @return liste des etudiants
   */
  
  public LinkedHashSet<Etudiant> getListeEtudiant() {
    
    return listeEtudiant;
    
  }
  
  /**
   * setter numero du groupe.
   *
   * @param numero numero du groupe
   */
  public void setNumero(int numero) {
    
    this.numero = numero;
    
  }
  
  /**
   * setter des etudiants.
   *
   * @param listeEtudiant liste
   */
  public void setListeEtudiant(LinkedHashSet<Etudiant> listeEtudiant) {
    
    this.listeEtudiant = listeEtudiant;
    
  }
  
  /**
   * setter annee de formation du groupe.
   *
   * @param anneeFormation l'annee de formation du groupe
   */
  public void setAnneeFormation(AnneeFormation anneeFormation) {
    
    this.anneeFormation = anneeFormation;
    
  }
  
  
  /**
   * constructeur groupe.
   *
   * @param annee annee de formation du groupe.
   */
  public Groupe(AnneeFormation annee) {
    
    listeEtudiant = new LinkedHashSet<>();
    
    this.anneeFormation = annee;
    
    
    
  }
  
  /**
   * fonction qui enleve des etudiants du groupe.
   *
   * @param etu etudiant a retirer du groupe.
   * 
   */
  
  
  
  public boolean removeEtudiant(Etudiant etu) {
    Iterator<Etudiant> iterator = listeEtudiant.iterator();
    while (iterator.hasNext()) {
      Etudiant etudiant = iterator.next();
      if (etu.getNumEtudiant() == etudiant.getNumEtudiant()) {
        iterator.remove();
        return true;
      }
    }
    return false;
  }
  
  
  public boolean removeEtudiant2(Etudiant etu) {
    for (Etudiant etudiant : listeEtudiant) {
      if (etu.getNumEtudiant() == etudiant.getNumEtudiant()) {
        return listeEtudiant.remove(etudiant);
      }
    }
    return false;
    
    
  }
  
  
}
