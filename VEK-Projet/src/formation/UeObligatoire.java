package formation;

import java.util.HashSet;
import java.util.Objects;

/**
 * Description des informations relatives a une UE Obligaoire : nom, responsable
 * et la liste d'etudiants inscrits.
 *
 * @author Eric Lefebvre
 */
public class UeObligatoire extends UniteEnseignement {
  
  /**
   * attribut de la sérialisation auto genere.
   */
  private static final long serialVersionUID = 7357551050514938813L;

  /**
   * Ajoute un etudiant e la liste d'etudiants de l'UE obligatoire et l'UE
   * obligatoire � la liste d'UE obligatoires de l'etudiant.
   *
   * @param etudiant l'etudiant a ajouter
   * @return <code>true</code> si l'ajout a ete fait, <code>false</code> en cas
   *         de probleme
   */
  public boolean addEtudiant(Etudiant etudiant) {
    if (etudiant == null) {
      return false;
    }
    
    boolean estAjoute = listeEtudiants.add(etudiant);
    
    if (estAjoute) {
      etudiant.addUeObl(this);
    }
    
    return estAjoute;
  }
  
  /**
   * Cree une UE obligatoire avec toutes ses informations.
   *
   * @param responsable le reesponsable de l'UE
   * @param nom le nom de l'UE
   * @param listeEtudiants la liste d'etudiants de l'UE
   */
  public UeObligatoire(Responsable responsable, String nom,
      HashSet<Etudiant> listeEtudiants) {
    super(responsable, nom, listeEtudiants);
  }
  
  /**
   * Cree une UE obligatoire avec toutes ses informations.
   *
   * @param responsable le reesponsable de l'UE
   * @param nom le nom de l'UE
   */
  public UeObligatoire(Responsable responsable, String nom) {
    super(responsable, nom, new HashSet<Etudiant>());
  }
}
