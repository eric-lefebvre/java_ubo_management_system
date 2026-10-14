package formation;

import java.io.Serializable;

/**
 * classe Responsable.
 *
 * @author Victor Quilgars.
 */

public class Responsable implements Serializable {
  
  /**
   * attribut de la sérialisation auto genere.
   */
  private static final long serialVersionUID = 1813041756150249021L;



  /**
   * Nom du responsable qui ne peut pas etre modifie.
   * 
   */
  private final String nom;
  
  
  
  /**
   * Prenom du responsable qui ne peut pas etre modifie.
   * 
   */
  private final String prenom;
  
  
  
  /**
   * Email du responsable qui ne peut pas etre modifie.
   * 
   */
  private String email;
  
  /**
   * année de formation que le responsable gère.
   */
  private AnneeFormation anneeFormation;
  
  
  
  /**
   * Constructeur qui cree un responsable avec son nom, prenom et adresse mail.
   *
   * @param nom nom du responsable
   * 
   * @param prenom prenom du responsable
   * 
   * @param email adresse mail du responsable
   * 
   */
  
  public Responsable(String nom, String prenom, String email) {
    
    this.nom = nom;
    
    this.prenom = prenom;
    
    this.email = email;
    
  }
  
  
  
  /**
   * Constructeur appelle l'autre constructeur avec des paramètres prédéfini.
   * 
   */
  
  public Responsable() {
    
    this("Eric", "Cariou", "eric.cariou@univ-brest.fr");
    
  }
  
  /**
   * constructeur qui acrée un responsable avce un pr�nom vide si aucun pr�nom
   * n'est fourni en paramètre.
   *
   * @param nom nom du responsable.
   * @param email email du responsable.
   */
  public Responsable(String nom, String email) {
    
    this(nom, "", email);
  }
  
  /**
   * constructeur qui acrée un responsable avce un pr�nom et un email vide si
   * aucun pr�nom ou email n'est fourni en paramètre.
   *
   * @param nom nom du responsable.
   */
  public Responsable(String nom) {
    
    this(nom, "", "");
  }
  
  
  
  /**
   * Fonction qui renvoie le nom du responsable.
   *
   * @return le nom du responsable
   * 
   */
  
  public String getNom() {
    
    return this.nom;
    
  }
  
  
  
  /**
   * Fonction qui renvoie le nom du responsable.
   *
   * @return le prenom du responsable
   * 
   */
  
  public String getPrenom() {
    
    return this.prenom;
    
  }
  
  /**
   * Fonction qui renvoie l'adresse email du responsable.
   *
   * @return l'adresse email du responsable.
   * 
   */
  
  public String getEmail() {
    
    return this.email;
    
  }
  
  
  /**
   * fonction toString.
   */
  public String toString() {
    
    return this.prenom + " " + this.nom;
    
  }
  
  /**
   * fonction equals.
   *
   * @param resp un responsable.
   * 
   * @return vrai si le responsable passé en paramètre est le même que celui de
   *         la classe appelante.
   *         
   */
  
  public boolean equals(Responsable resp) {
    
    if (this.prenom == resp.prenom) {
      
      if (this.nom == resp.nom) {
        
        if (this.email == resp.email) {
          
          return true;
          
        }
        
      }
      
    }
    
    return false;
    
  }
  
  
  
  /**
   * Modifie l'adresse email du responsable.
   *
   * @param email nouvelle adresse email du responsable.
   * 
   */
  
  public void setEmail(String email) {
    
    if (email != null) {
      
      this.email = email;
      
    }
    
  }
  
}

