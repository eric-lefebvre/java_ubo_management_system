package formation;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

import formation.Etudiant;

/**
 * Description des informations relatives a une Annee de formation :
 * responsable, nom, annee, liste d'UE Optionnelles, liste d'UE Obligatoires,
 * liste de groupes de TD, liste de groupes de TP, liste d'etudiants, nombre de
 * choix d'options, taille maximal d'un groupe de TP/TD.
 *
 * @author Victor Quilgars.
 *
 */

public class AnneeFormation implements Serializable {
  
  /**
   * attribut de la sérialisation auto genere.
   */
  private static final long serialVersionUID = -7099105073512152263L;
  
  /**
   * reference static a l'object AnneeFormation.
   * 
   */
  
  public static AnneeFormation instance;
  
  /**
   * responsable de la formation.
   * 
   */
  
  private Responsable responsable;
  
  /**
   * nom de la formation.
   * 
   */
  
  private String nom;
  
  /**
   * annee de la formation.
   * 
   */
  
  final int annee;
  
  /**
   * liste des UE optionnelles de la formation.
   * 
   */
  
  private HashSet<UeOptionnelle> listeUeOption;
  
  /**
   * liste des UE obligatoires de la formation.
   * 
   */
  
  private HashSet<UeObligatoire> listeUeObligatoire;
  
  /**
   * liste de groupe de TP.
   * 
   */
  
  private LinkedHashSet<GroupeTp> listeGroupeTp;
  
  /**
   * liste de groupe de TD.
   * 
   */
  
  private LinkedHashSet<GroupeTd> listeGroupeTd;
  /**
   * liste des etudiants qui sont dans la formation.
   */
  
  private LinkedHashSet<Etudiant> listeEtudiant;
  /**
   * nombre d'UE optionnelles que l'etudiant doit choisir.
   * 
   */
  
  private int nombreChoixOption;
  
  /*
   * nombre d'etudiants max dans une groupe de tp.
   * 
   */
  
  private int tailleMaxTp;
  
  /**
   * nombre d'etudiants max dans un groupe de td.
   * 
   */
  
  private int tailleMaxTd;
  
  /**
   * constructeur minimal.
   *
   * @param nomFormation nom de la formation
   * @param annee anne de la formation
   * @param resp responsable de la formation
   */
  public AnneeFormation(String nomFormation, int annee, Responsable resp) {
    
    this.nom = nomFormation;
    
    this.annee = annee;
    
    this.responsable = resp;
    
    this.tailleMaxTd = -1;
    
    this.tailleMaxTp = -1;
    
    this.nombreChoixOption = -1;
    
    instance = this;
    
    this.listeGroupeTd = new LinkedHashSet<>();
    
    this.listeGroupeTp = new LinkedHashSet<>();
    
    this.listeEtudiant = new LinkedHashSet<>();
    
    this.listeUeObligatoire = new HashSet<>();
    
    this.listeUeOption = new HashSet<>();
    
  }
  
  /**
   * constructeur avec tous les paramètres.
   *
   * @param nomFormation nom de la formation
   * @param annee anne de la formation
   * @param resp responsable de la formation
   * @param tailleTd taille max d'un groupe de tp.
   * @param tailleTp taille max d'un groupe de td.
   * @param choix nombre de choix pour les option.
   */
  public AnneeFormation(String nomFormation, int annee, Responsable resp,
      int tailleTd, int tailleTp, int choix) {
    
    this(nomFormation, annee, resp);
    
    this.tailleMaxTd = tailleTd;
    
    this.tailleMaxTp = tailleTp;
    
    this.nombreChoixOption = choix;
    
  }
  
  /**
   * le getter de l'annee de la formation.
   *
   * @return annee de la formation
   */
  public int getAnnee() {
    return this.annee;
  }
  
  /**
   * getteer responsable.
   *
   * @return responsable de la formation
   */
  
  public Responsable getResponsable() {
    
    return this.responsable;
    
  }
  
  /**
   * getter nom.
   *
   * @return nom de la formation.
   * 
   */
  
  public String getNom() {
    
    if (nom != null) {
      return this.nom;
    } else {
      return "";
    }
    
  }
  
  /**
   * getter de la liste des UE obligatoires de la formation.
   *
   * @return liste des UE obligatoires de la formation
   * 
   */
  
  public HashSet<UeObligatoire> getListeUeObligatoire() {
    
    return listeUeObligatoire;
    
  }
  
  /**
   * getter liste UeOptionnelles.
   *
   * @return liste des UE optionnelles de la formation.
   * 
   */
  
  public HashSet<UeOptionnelle> getListeUeOption() {
    
    return listeUeOption;
    
  }
  
  /**
   * getter de de tous les etudiants qui sont dans la formation.
   *
   * @return liste des etudiants de la formation.
   */
  
  public LinkedHashSet<Etudiant> getListeEtudiant() {
    
    return listeEtudiant;
    
  }
  
  /**
   * getter du nombre d'option que l'etudiant doit choisir.
   *
   * @return nombre d'options que l'etudiant doit choisir.
   * 
   */
  
  public int getNombreChoixOption() {
    
    return nombreChoixOption;
    
  }
  
  /**
   * getter de la taille maximale d'un groupe de tp.
   *
   * @return taille max d'un groupe de tp.
   * 
   */
  
  public int getTailleMaxTp() {
    
    return tailleMaxTp;
    
  }
  
  /**
   * getter de la taille maximale d'un groupe de td.
   *
   * @return taille max d'un groupe de td.
   * 
   */
  
  public int getTailleMaxTd() {
    
    return tailleMaxTd;
    
  }
  
  /**
   * getter de la taille maximale d'un groupe de td.
   *
   * @return taille max d'un groupe de td.
   * 
   */
  
  public LinkedHashSet<GroupeTd> getListeGroupeTd() {
    
    return listeGroupeTd;
    
  }
  
  /**
   * getter de la taille maximale d'un groupe de td.
   *
   * @return taille max d'un groupe de td.
   * 
   */
  
  public LinkedHashSet<GroupeTp> getListeGroupeTp() {
    
    return listeGroupeTp;
    
  }
  
  /**
   * renvoie un groupe de td par rapport à  son numero
   * @param num numero de groupe
   * @return groupe de td
   */
  public GroupeTd getGroupeTd(int num) {
	  for(GroupeTd groupe : this.getListeGroupeTd()) {
		  if(groupe.getNumero() == num) {
			  return groupe;
		  }
	  }
	  return null;
  }
  
  /**
   * renvoie un groupe de tp par rapport à  son numero
   * @param num numero de groupe
   * @return groupe de tp
   */
  public GroupeTp getGroupeTp(int num) {
	  for(GroupeTp groupe : this.getListeGroupeTp()) {
		  if(groupe.getNumero() == num) {
			  return groupe;
		  }
	  }
	  return null;
  }
  
  /**
   * setter nom.
   *
   * @param nom nom de la formation a definir.
   * 
   */
  
  public void setNom(String nom) {
    
    if (nom != null) {
      this.nom = nom;
    }
  }
  
  /**
   * setter de de tous les etudiants qui sont dans la formation.
   *
   */
  
  public void setListeEtudiant(LinkedHashSet<Etudiant> liste) {
    
    this.listeEtudiant = liste;
    
  }
  
  /**
   * setter responsable .
   *
   * @param responsable responsable de l'annee de formation.
   */
  public void setResponsable(Responsable responsable) {
    
    if (responsable != null) {
      this.responsable = responsable;
    }
    
  }
  
  /**
   * setter liste des options.
   *
   * @param listeUeOption liste des UE optionnelles de la formation a definir.
   * 
   */
  
  public void setListeUeOption(HashSet<UeOptionnelle> listeUeOption) {
    
    this.listeUeOption = listeUeOption;
    
  }
  
  /**
   * setter UE obligatoire.
   *
   * @param listeUeObligatoire liste des UE obligatoires a definir de la
   *        formation.
   * 
   */
  
  public void setListeUeObligatoire(
      
      HashSet<UeObligatoire> listeUeObligatoire) {
    
    if (listeUeObligatoire != null) {
      this.listeUeObligatoire = listeUeObligatoire;
    }
    
  }
  
  /**
   * setter du nombre d'option que l'etudiant doit choisir.
   *
   * @param nombreChoixOption nombre d'option optionnelle que l'etudiant doit
   *        choisir.
   * 
   */
  public void setNombreChoixOption(int nombreChoixOption) {
    
    if (nombreChoixOption > 0) {
      this.nombreChoixOption = nombreChoixOption;
    }
    
  }
  
  /**
   * setter de la taille maximale d'un groupe de Tp.
   *
   * @param taille la nouvelle taille maximale
   * 
   */
  public void setTailleMaxTp(int taille) {
    
    if (taille > 0) {
      this.tailleMaxTp = taille;
    }
    
  }
  
  /**
   * setter de la taille maximale d'un groupe de Td.
   *
   * @param taille la nouvelle taille maximale
   * 
   */
  public void setTailleMaxTd(int taille) {
    
    if (taille > 0) {
      this.tailleMaxTd = taille;
    }
    
  }
  
  /**
   * methode qui ajoute un groupe de TP � la liste de groupe de TP.
   *
   * @return <code>true</code> si l'ajout a ete fait, <code>false</code> en cas
   *         de probleme
   */
  public boolean addGroupeTp(GroupeTp groupeTp) {
    
    if (groupeTp == null) {
      return false;
    } else {
      this.listeGroupeTp.add(groupeTp);
      return true;
    }
    
  }
  
  /**
   * methode qui ajoute un groupe de TD � la liste de groupe de TD.
   *
   * @return <code>true</code> si l'ajout a ete fait, <code>false</code> en cas
   *         de probleme
   */
  public boolean addGroupeTd(GroupeTd groupeTd) {
    
    if (groupeTd == null) {
      return false;
    } else {
      this.listeGroupeTd.add(groupeTd);
      return true;
    }
    
  }
  
  
  /**
   * methode qui ajoute une UE Obligatoire � la liste d'UE Obligatoires.
   *
   * @return <code>true</code> si l'ajout a ete fait, <code>false</code> en cas
   *         de probleme
   */
  public boolean addUeObligatoire(UeObligatoire ue) {
    
    if (ue == null) {
      return false;
    } else {
      return this.listeUeObligatoire.add(ue);
    }
    
  }
  
  /**
   * methode qui ajoute une UE Optionnelle � la liste d'UE Optionnelles.
   *
   * @return <code>true</code> si l'ajout a ete fait, <code>false</code> en cas
   *         de probleme
   */
  public boolean addUeOptionnelle(UeOptionnelle ue) {
    
    if (ue == null) {
      return false;
    } else {
      return this.listeUeOption.add(ue);
    }
    
  }
  
  /**
   * methode qui ajoute un nouvel etudiant dans la liste.
   *
   * @param etu un objet de type etudiant
   *
   * @return -1 si probleme, l'identidiant de l'etudiant ajoute sinon
   *
   * @author Katia
   */
  public int addNouvEtu(Etudiant etu) {
    if (etu == null) {
      return -1;
    } else {
      this.listeEtudiant.add(etu);
      return etu.getNumEtudiant();
    }
    
  }
  
  /**
   * setter de la liste de groupe de TD.
   *
   * @param liste une liste de groupe de TD
   */
  public void setListeGroupeTd(LinkedHashSet<GroupeTd> liste) {
    if (liste != null) {
      this.listeGroupeTd = liste;
    }
  }
  
  /**
   * setter de la liste de groupe de TP.
   *
   * @param liste une liste de groupe de TP
   */
  public void setListeGroupeTp(LinkedHashSet<GroupeTp> liste) {
    if (liste != null) {
      this.listeGroupeTp = liste;
    }
  }
  
}
