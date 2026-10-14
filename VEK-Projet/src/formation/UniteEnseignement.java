package formation;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

/**
 * Description des informations relatives a une Unite d'enseignement : nom,
 * responsable et la liste d'etudiants inscrits.
 *
 * @author Eric Lefebvre
 */
public abstract class UniteEnseignement implements Serializable {
  
  /**
   * attribut de la sérialisation auto genere.
   */
  private static final long serialVersionUID = -4336983364258451333L;

  /**
   * Reference au responsable de l'UE.
   */
  protected Responsable responsable;
  
  /**
   * Nom de l'UE.
   */
  protected String nom;
  
  /**
   * Liste d'etudiants de l'UE.
   */
  protected HashSet<Etudiant> listeEtudiants;
  
  /**
   * Renvoie le responsable de l'UE.
   *
   * @return le responsable de l'UE
   */
  public Responsable getResponsable() {
    return responsable;
  }
  
  /**
   * Renvoie le nom de l'UE.
   *
   * @return le nom de l'UE
   */
  public String getNom() {
    return nom;
  }
  
  /**
   * Renvoie la liste d'etudiants de l'UE.
   *
   * @return la liste d'etudiants de l'UE
   */
  public HashSet<Etudiant> getListeEtudiants() {
    return listeEtudiants;
  }
  
  /**
   * Modifie le responsable de l'UE.
   *
   * @param responsable le nouveau responsable (doit etre non null)
   */
  public void setResponsable(Responsable responsable) {
    if (responsable != null) {
      this.responsable = responsable;
    }
  }
  
  /**
   * Modifie le nom de l'UE.
   *
   * @param nom le nouveau nom (doit etre non null)
   */
  public void setNom(String nom) {
    if (nom != null) {
      this.nom = nom;
    } else {
      this.nom = "";
    }
  }
  
  /**
   * Modifie la liste d'etudiants de l'UE.
   *
   * @param listeEtudiants la nouvelle liste d'etudiants
   */
  public void setListeEtudiants(HashSet<Etudiant> listeEtudiants) {
    this.listeEtudiants = listeEtudiants;
  }
  
  /**
   * Cree une unite d'enseignement avec ses informations obligatoires.
   *
   * @param responsable le reesponsable de l'UE
   * @param nom le nom de l'UE
   */
  public UniteEnseignement(Responsable responsable, String nom) {
    this(responsable, nom, new HashSet<Etudiant>());
  }
  
  /**
   * Cree une unite d'enseignement avec toutes ses informations.
   *
   * @param responsable le reesponsable de l'UE
   * @param nom le nom de l'UE
   * @param listeEtudiants la liste d'etudiants de l'UE
   */
  public UniteEnseignement(Responsable responsable, String nom,
      HashSet<Etudiant> listeEtudiants) {
    super();
    if (nom == null) {
      nom = "";
    }
    this.responsable = responsable;
    this.nom = nom;
    this.listeEtudiants = listeEtudiants;
  }
  
  @Override
  public String toString() {
    return this.nom + ", responsable: "
        + this.responsable;
  }
  
  @Override
  public int hashCode() {
    // La liste d'etudiant cause une boucle infinie si elle est hachee
    return Objects.hash(nom, responsable);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    UniteEnseignement other = (UniteEnseignement) obj;
    return Objects.equals(listeEtudiants, other.listeEtudiants)
        && Objects.equals(nom, other.nom)
        && Objects.equals(responsable, other.responsable);
  }
  
  /**
   * Retire un etudiant de la liste d'etudiants de l'UE.
   *
   * @param etudiant l'etudiant a retirer
   * @return <code>true</code> si le retirage a ete fait, <code>false</code> en
   *         cas de probleme
   */
  public boolean removeEtudiant(Etudiant etudiant) {
    if (etudiant == null) {
      return false;
    }
    return listeEtudiants.remove(etudiant);
  }
}
