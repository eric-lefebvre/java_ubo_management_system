package formation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * class étudiant qui gère un etudiant et toutes les informations le
 * concernant.
 *
 * @author Katia
 */
public class Etudiant implements Serializable {
  /**
   * attribut de la sérialisation auto genere.
   */
  private static final long serialVersionUID = -3130672755679649381L;
  /**
   * attribut de classe étudiant qui stocke l'id du dernier étudiant crée.
   * 
   */
  private static int numeroEtudiantAUTOINCR = 0;
  /**
   * attribut du numéro étudiant qui est unique.
   * 
   */
  private int numeroEtudiant = 0;
  /**
   * attribut du mot de passe du compte étudiant.
   */
  private String mdp;
  
  /**
   * attribut qui associe à un etudiant ses informations personnelles.
   */
  InformationPersonnelle infoPersoEtudiant;
  
  /**
   * attribut qui associe à un etudiant la formation où il souhaite
   * s'inscrire.
   */
  AnneeFormation inscription;
  
  /**
   * attributs qui associent chaque étudiant à un groupe de TD et de TP.
   */
  private int groupeTd;
  private int groupeTp;
  
  /**
   * attributs qui associent chaque étudiant à des unités d'enseignements.
   * obligatoires et libres
   */
  private Set<UeObligatoire> ueObl;
  private Set<UeOptionnelle> ueOpt;
  
  /*
   * attribut liste des messages lues 
   */
  List<String> messagesTous;
  
  /*
   * attribut liste des message non lus
   */
  List<String> messagesNonLus;
  
  // getteurs, setter, equals, toString, constructeur
  
  /*
   * Constructeur : cree un etudiant avec ses informations personnelles
   * obligatoires, les attributs qui définissient les groupes et les ues sont
   * inisialisés à null au début car l'étudiant n'a pas encore choisi ses
   * ues et ses groupes.
   * 
   * @param mdp
   */
  
  /**
   * Constructeur : cree un etudiant avec ses toutes informations personnelles.
   * numero etudiant auto incremete annee d'inscription : instace globale (une
   * seule nnee geree par le systeme) les groupe de tp et td sont initialisé à
   * -1 (non affecte) ueOblig : ceux de la formation
   *
   * @param infos information personelle complete ou non
   * @param motDePasse mot de passe de l'etudiant
   */
  public Etudiant(InformationPersonnelle infos, String motDePasse) {
    this.numeroEtudiant = ++Etudiant.numeroEtudiantAUTOINCR;
    this.infoPersoEtudiant = infos;
    this.mdp = motDePasse;
    this.inscription = AnneeFormation.instance;
    this.groupeTd = -1;
    this.groupeTp = -1;
    this.ueObl = AnneeFormation.instance.getListeUeObligatoire();
    this.ueOpt = new HashSet<>();
    this.messagesTous = new ArrayList<>();
    this.messagesNonLus = new ArrayList<>();
    
  }
  
  // getteur des attributs :
  
  /**
   * get de l'attribut numero étudiant.
   */
  public int getNumEtudiant() {
    return numeroEtudiant;
  }
  
  /**
   * get de l'attribut du mdp de l'etudiant.
   */
  public String getMdp() {
    return mdp;
  }
  
  /**
   * get de l'attribut de l'annee de formation à laquelle l'étudiant est
   * inscrit.
   */
  public AnneeFormation getInscription() {
    return inscription;
  }
  
  /**
   * get de l'attribut des informations personnelle.
   */
  public InformationPersonnelle getInfoPersoEtudiant() {
    return infoPersoEtudiant;
  }
  
  /**
   * get de l'attribut Ue oblig de la formation à laquelle l'etudiant est
   * inscrit.
   */
  public Set<UeObligatoire> getUes() {
    return ueObl;
  }
  
  /**
   * get de l'attribut Ue optionnelle de la formation à laquelle l'etudiant est
   * inscrit et qu'il a choisit.
   */
  public Set<UeOptionnelle> getUeopt() {
    return ueOpt;
  }
  
  /**
   * get de l'attribut du groupe de Td de l'étudiant.
   */
  public int getGroupeTd() {
    return groupeTd;
  }
  
  /**
   * get de l'attribut du groupe de Tp de l'etudiant.
   */
  public int getGroupeTp() {
    return groupeTp;
  }
  
  // setteur des attributs :
  /**
   * set de l'attribut des informations personnelle.
   *
   * @param infoPersoEtudiant l'information personnelle de l'etudiant
   */
  public void setInfoPersoEtudiant(InformationPersonnelle infoPersoEtudiant) {
    this.infoPersoEtudiant = infoPersoEtudiant;
  }
  
  /**
   * set de l'attribut de l'annee de formation à laquelle l'étudiant est
   * inscrit.
   *
   * @param inscription l'annee de formation
   */
  public void setInscription(AnneeFormation inscription) {
    this.inscription = inscription;
  }
  
  /**
   * set de la liste d'ue obligatoires a laquelle l'etudiant est inscrit.
   *
   * @param setUeObl la liste d'ue obligatoires
   */
  public void setUeObl(Set<UeObligatoire> setUeObl) {
    this.ueObl = setUeObl;
  }
  
  /**
   * set de la liste d'ue optionnelles a laquelle l'etudiant est inscrit.
   *
   * @param setUeOpt la liste d'ue optionnelles
   */
  public void setUeOpt(Set<UeOptionnelle> setUeOpt) {
    this.ueOpt = setUeOpt;
  }
  
  /**
   * fonction qui ajoute une ueObligatoire a la liste d'ue obligatoires de
   * l'etudiant.
   *
   * @param ue l'ue a ajouter
   *
   * @return <code>true</code> si l'ajout a ete fait, <code>false</code> en cas
   *         de probleme
   */
  public boolean addUeObl(UeObligatoire ue) {
    if (ue != null) {
      return this.ueObl.add(ue);
    }
    return false;
  }
  
  /**
   * fonction qui ajoute une ueOptionnelle a la liste d'ue optionnelles de
   * l'etudiant.
   *
   * @param ue l'ue a ajouter
   *
   * @return <code>true</code> si l'ajout a ete fait, <code>false</code> en cas
   *         de probleme
   */
  public boolean addUeOpt(UeOptionnelle ue) {
    if (ue != null) {
      return this.ueOpt.add(ue);
    }
    return false;
  }
  
  /**
   * set de l'attribut du groupe de Td de l'étudiant.
   *
   * @param groupeTd le groupe de TD
   */
  public void setGroupeTd(int groupeTd) {
    this.groupeTd = groupeTd;
  }
  
  @Override
  public String toString() {
    return infoPersoEtudiant + ", numEtu: " + numeroEtudiant + ", TD: "
        + groupeTd + ", TP: " + groupeTp;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(groupeTd, groupeTp, infoPersoEtudiant, inscription, mdp,
        ueObl, ueOpt);
  }
  
  /**
   * redefinition de la methode equals : deux etudiant sont egaux uniquement
   * s'ils ont le même identifiant.
   *
   * @param obj l'objet a comparer
   */
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
    Etudiant other = (Etudiant) obj;
    return this.numeroEtudiant == other.numeroEtudiant;
  }
  
  /**
   * set de l'attribut du groupe de Tp de l'étudiant.
   *
   * @param groupeTp le groupe de TP
   */
  public void setGroupeTp(int groupeTp) {
    this.groupeTp = groupeTp;
  }
  
  /**
   * fonction qui recupère le numéro du grp de td.
   *
   * @param groupeTd le groupe de TD
   */
  public int getNumGroupTd(GroupeTd groupeTd) {
    return groupeTd.getNumero();
  }
  
  /**
   * fonction qui recupère le numéro du grp de tp.
   *
   * @param groupeTp le groupe de TP
   */
  public int getNumGroupTp(GroupeTp groupeTp) {
    return groupeTp.getNumero();
  }
  
 
  
  /**
   * fonction qui ajoute un message dans les listes des messages de l'étudiant.
   *
   * @param texte le message a ajouter
   */
  public void addMessage(String texte) {
    this.messagesNonLus.add(texte);
    this.messagesTous.add(texte);
  }
  
}
