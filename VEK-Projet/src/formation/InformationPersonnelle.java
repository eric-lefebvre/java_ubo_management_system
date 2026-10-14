package formation;

import java.util.Objects;

/**
 * Description des informations personnelles d'un etudiant : identite, age et
 * adresse.
 *
 * @author Eric Cariou
 */
public final class InformationPersonnelle implements java.io.Serializable {
  
  /**
   * Identifiant de serialisation.
   */
  private static final long serialVersionUID = 4026408353251835506L;
  
  /**
   * Le nom de la personne (ne peut pas etre modifie).
   */
  private final String nom;
  
  /**
   * Le prenom de la personne (ne peut pas etre modifie).
   */
  private final String prenom;
  
  /**
   * L'age de la personne (la valeur 0 correspond a un age non defini).
   */
  private int age;
  
  /**
   * L'adresse de la personne (une chaine vide "" correspond a une adresse non
   * definie).
   */
  private String adresse;
  
  /**
   * Renvoie le nom de la personne.
   *
   * @return le nom de la personne
   */
  public String getNom() {
    return nom;
  }
  
  /**
   * Renvoie le prenom de la personne.
   *
   * @return le prenom de la personne
   */
  public String getPrenom() {
    return prenom;
  }
  
  /**
   * Renvoie l'age de la personne.
   *
   * @return l'age de la personne
   */
  public int getAge() {
    return age;
  }
  
  /**
   * Modifie l'age de la personne.
   *
   * @param age le nouvel age (doit etre superieur a 0)
   */
  public void setAge(int age) {
    if (age > 0) {
      this.age = age;
    }
  }
  
  /**
   * Renvoie l'adresse de la personne.
   *
   * @return l'adresse de la personne
   */
  public String getAdresse() {
    return adresse;
  }
  
  /**
   * Modifie l'adresse de la personne.
   *
   * @param adresse la nouvelle adresse (doit etre differente de null)
   */
  public void setAdresse(String adresse) {
    if (adresse != null) {
      this.adresse = adresse;
    }
  }
  
  /**
   * Cree une personne avec ses informations obligatoires.
   *
   * @param nom le nom de la personne
   * @param prenom le prenom de la personne
   */
  public InformationPersonnelle(String nom, String prenom) {
    this(nom, prenom, "", 0);
  }
  
  /**
   * Cree une personne avec toutes ses informations.
   *
   * @param nom le nom de la personne
   * @param prenom le prenom de la personne
   * @param adresse l'adresse de la personne
   * @param age l'age de la personne
   */
  public InformationPersonnelle(String nom, String prenom, String adresse,
      int age) {
    super();
    this.nom = nom;
    this.prenom = prenom;
    this.adresse = adresse;
    this.age = age;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(adresse, age, nom, prenom);
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    InformationPersonnelle other = (InformationPersonnelle) obj;
    return Objects.equals(adresse, other.adresse) && age == other.age
        && Objects.equals(nom, other.nom)
        && Objects.equals(prenom, other.prenom);
  }
  
  @Override
  public String toString() {
    return prenom + " " + nom;
  }
}
