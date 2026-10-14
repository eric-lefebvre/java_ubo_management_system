package formation;

import java.util.HashSet;
import java.util.Objects;

/**
 * Description des informations relatives e une UE Optionnelle : nom,
 * responsable, la liste d'etudiants inscrits et le nombre maximum d'etudiants.
 *
 * @author Eric Lefebvre
 */
public class UeOptionnelle extends UniteEnseignement {
  
  /**
   * attribut de la sérialisation auto genere.
   */
  private static final long serialVersionUID = -3887082387898928348L;
  private final int maxEtudiants;
  
  /**
   * Cree une UE optionnelle avec toutes ses informations.
   *
   * @param responsable le reesponsable de l'UE
   * @param nom le nom de l'UE
   * @param listeEtudiants la liste d'etudiants de l'UE
   * @param maxEtudiants le nombre maximum d'etudiants pouvant choisir cette UE
   */
  public UeOptionnelle(Responsable responsable, String nom,
      HashSet<Etudiant> listeEtudiants, int maxEtudiants) {
    super(responsable, nom, listeEtudiants);
    if (maxEtudiants > 0) {
      this.maxEtudiants = maxEtudiants;
    } else {
      throw new IllegalArgumentException(
          "Le maximum d'etudiants ne peut pas etre negatif");
    }
    
  }
  
  /**
   * Cree une UE optionnelle avec ses informations obligatoires.
   *
   * @param responsable le reesponsable de l'UE
   * @param nom le nom de l'UE
   * @param maxEtudiants le nombre maximum d'etudiants pouvant choisir cette UE
   */
  public UeOptionnelle(Responsable responsable, String nom, int maxEtudiants) {
    this(responsable, nom, new HashSet<Etudiant>(), maxEtudiants);
  }
  
  /**
   * Renvoie le nomre maximum d'etudiants de l'UE.
   *
   * @return le nom de l'UE
   */
  public int getMaxEtudiants() {
    return maxEtudiants;
  }
  
  @Override
  public String toString() {
    return this.nom + ", responsable: "
        + this.responsable + ", places: " + this.maxEtudiants;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + Objects.hash(maxEtudiants);
    return result;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    UeOptionnelle other = (UeOptionnelle) obj;
    return maxEtudiants == other.maxEtudiants;
  }
  
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
    
    if (listeEtudiants.size() >= maxEtudiants) {
      return false;
    }
    
    boolean estAjoute = listeEtudiants.add(etudiant);
    
    if (estAjoute) {
      etudiant.addUeOpt(this);
    }
    
    return estAjoute;
  }
}
