package formation;

/**
 * sous-classe groupe de tp qui herite de groupe.
 *
 * @author victor.quilgars
 *
 */
public class GroupeTp extends Groupe {
  
  /**
   * attribut de la sérialisation auto genere.
   */
  private static final long serialVersionUID = -8138646598124972869L;
  /**
   * Auto increment pour le numero du groupe.
   * 
   */
  public static int AutoIncrementTp = 0;
  
  /**
   * constructeur tp.
   *
   * @param annee l'annee de formation du groupe.
   */
  public GroupeTp(AnneeFormation annee) {
    super(annee);
    annee.addGroupeTp(this);
    this.tailleMax = annee.getTailleMaxTp();
    this.numero = ++AutoIncrementTp;
  }
  
  
  
  @Override
  public String toString() {
    return "GroupeTp" + numero + "[" + this.listeEtudiant.size()
        + " etudiants]";
  }
  
  /**
   * Fonction qui ajoute un etudiant au groupe.
   *
   * @param etu etudiant e ajouter au groupe.
   * 
   * @return <code>true</code> si l'ajout a ete fait, <code>false</code> en cas
   *         de probleme
   */
  
  public boolean ajouterEtudiant(Etudiant etu) {
    
    if (listeEtudiant.size() < tailleMax) {
      
      listeEtudiant.add(etu);
      etu.setGroupeTp(this.numero);
      return true;
      
    } else {
      
      return false;
      
    }
    
  }
}
