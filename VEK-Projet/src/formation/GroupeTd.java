package formation;

/**
 * sous-classe groupe de td.
 *
 * @author victor.quilgars
 *
 */
public class GroupeTd extends Groupe {
  
  /**
   * attribut de la sérialisation auto genere.
   */
  private static final long serialVersionUID = -8440773237892631445L;
  /**
   * Auto increment pour le numero du groupe.
   * 
   */
  public static int AutoIncrementTd = 0;
  
  /**
   * constructeur td.
   *
   * @param annee l'annee de formation du groupe.
   */
  public GroupeTd(AnneeFormation annee) {
    super(annee);
    annee.addGroupeTd(this);
    this.tailleMax = annee.getTailleMaxTd();
    this.numero = ++AutoIncrementTd;
  }
  
  
  @Override
  public String toString() {
    return "GroupeTd" + numero + "[" + this.listeEtudiant.size()
        + " �tudiants]";
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
      etu.setGroupeTd(this.numero);
      return true;
      
    } else {
      
      return false;
      
    }
    
  }
  
  
  
  /*
   * à faire : fonction qui recupère le numéro du grp de td et tp
   */
}
