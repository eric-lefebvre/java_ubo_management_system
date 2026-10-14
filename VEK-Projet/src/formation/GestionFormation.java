package formation;

import formation.AnneeFormation;
import formation.Etudiant;
import formation.Responsable;
import formation.UeObligatoire;
import formation.UeOptionnelle;
import formation.UniteEnseignement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Classe d'implementation de l'interface InterGestionFormation qui permet de
 * gerer l'annee de formation.
 *
 * @author Eric Lefebvre.
 *
 */
public class GestionFormation implements InterGestionFormation {
  
  /**
   * constructeur vide.
   *
   */
  public GestionFormation() {
    super();
    
  }
  
  /**
   * annee de formation.
   */
  private AnneeFormation anneeFormation;
  
  /**
   * Cree une (annee de) formation avec son nom et celui du responsable. Si une
   * formation existait deje dans le systeme, la nouvelle la remplace et efface
   * la precedente.
   *
   * @param nomFormation le nom de la formation (chaine non vide)
   * @param nomResponsable le nom et prenom du responsable (chaine non vide)
   * @param email l'email du responsable (adresse email valide)
   */
  public void creerFormation(String nomFormation, String nomResponsable,
      String email) {
    this.anneeFormation = new AnneeFormation(nomFormation, 2023,
        new Responsable(nomResponsable, email));
    GroupeTd.AutoIncrementTd = 0;
    GroupeTp.AutoIncrementTp = 0;
  }
  
  /**
   * Renvoie l'annee de fomation gerer par cette gestion.
   *
   * @return l'annee de formation
   */
  public AnneeFormation getAnneeFormation() {
    return this.anneeFormation;
  }
  
  /**
   * Renvoie le nom du responsable de formation.
   *
   * @return le nom du responsable de formation ou <code>null</code> s'il n'a
   *         pas ete defini
   */
  public String getNomResponsableFormation() {
    return this.anneeFormation.getResponsable().getNom();
  }
  
  /**
   * Renvoie l'adresse email du responsable de formation.
   *
   * @return l'adresse email du responsable de formation ou <code>null</code> si
   *         elle n'a pas ete definie
   */
  public String getEmailResponsableFormation() {
    return this.anneeFormation.getResponsable().getEmail();
  }
  
  /**
   * Renvoie le nom de la formation.
   *
   * @return le nom de la formation
   */
  public String getNomFormation() {
    return this.anneeFormation.getNom();
  }
  
  
  /**
   * Rajoute une UE obligatoire a la formation. L'UE ne doit pas deja etre dans
   * la liste des UE de la formation (ni en obligatoire, ni en optionnel).
   *
   * @param ue l'UE a rajouter
   * @return <code>true</code> si l'ajout a ete fait, <code>false</code> en cas
   *         de probleme
   */
  public boolean ajouterEnseignementObligatoire(UniteEnseignement ue) {
    
    if (this.anneeFormation.getListeUeOption().contains(ue)) {
      return false;
    }
    
    UeObligatoire ueObl =
        new UeObligatoire(ue.getResponsable(), ue.getNom());
    
    return this.anneeFormation.addUeObligatoire(ueObl);
  }
  
  /**
   * Rajoute une UE optionnelle a la formation. L'UE ne doit pas deja etre dans
   * la liste des UE de la formation (ni en obligatoire, ni en optionnel).
   *
   * @param ue l'UE a rajouter
   * @param nbPlaces le nombre de places maximum dans l'option (nombre superieur
   *        a 1) ou 0 pour preciser qu'il n'y a pas de limite de places
   * @return <code>true</code> si l'ajout a ete fait, <code>false</code> en cas
   *         de probleme
   */
  public boolean ajouterEnseignementOptionnel(UniteEnseignement ue,
      int nbPlaces) {
    
    if (this.anneeFormation.getListeUeOption().contains(ue)) {
      return false;
    }
    
    UeOptionnelle ueOpt = new UeOptionnelle(
        ue.getResponsable(), ue.getNom(), nbPlaces);
    
    return this.anneeFormation.addUeOptionnelle(ueOpt);
  }
  
  /**
   * Definit le nombre d'options que doit choisir un etudiant. Ne peut plus etre
   * modifie une fois defini.
   *
   * @param nombre le nombre d'options e choisir pour un etudiant (nombre
   *        superieur ou egal a 1)
   */
  public void definirNombreOptions(int nombre) {
    
    if (nombre >= 1) {
      this.anneeFormation.setNombreChoixOption(nombre);
    }
  }
  
  /**
   * Definit le nombre de places dans un groupe de TD. Ne peut plus etre modifie
   * une fois defini.
   *
   * @param taille le nombre de place dans un groupe de TD (nombre superieur a
   *        1)
   */
  public void setTailleGroupeDirige(int taille) {
    
    if (taille >= 1) {
      this.anneeFormation.setTailleMaxTd(taille);
    }
  }
  
  /**
   * Definit le nombre de places dans un groupe de TP. Ne peut plus etre modifie
   * une fois defini.
   *
   * @param taille le nombre de place dans un groupe de TP (nombre superieur a
   *        1)
   */
  public void setTailleGroupePratique(int taille) {
    
    if (taille >= 1) {
      this.anneeFormation.setTailleMaxTp(taille);
    }
  }
  
  /**
   * Renvoie le nombre de places dans un groupe de TD.
   *
   * @return le nombre de places dans un groupe de TD ou -1 s'il n'a pas encore
   *         ete defini
   */
  public int getTailleGroupeDirige() {
    return this.anneeFormation.getTailleMaxTd();
  }
  
  /**
   * Renvoie le nombre de places dans un groupe de TP.
   *
   * @return le nombre de places dans un groupe de TP ou -1 s'il n'a pas encore
   *         ete defini
   */
  public int getTailleGroupePratique() {
    return this.anneeFormation.getTailleMaxTp();
  }
  
  /**
   * Attribue automatiquement les etudiants non encore affectes e des groupes de
   * TD et de TP. Au besoin, cree de nouveaux groupes de TD ou de TP. Pour
   * harmoniser la taille des groupes, des etudiants deja places peuvent etre
   * deplaces. Les etudiants concernes par une affectation ou un changement
   * d'affectation recoivent un message pour leur preciser ce qu'il s'est passe.
   */
  public void attribuerAutomatiquementGroupes() {
    // Récupérer la liste des groupes de TD et TP de l'année de formation
    
    
    
    // Parcourir les étudiants non encore affectés
    LinkedHashSet<Etudiant> etudiantsNonAffectesTd =
        new LinkedHashSet<>(this.getEtudiantNonAffecteTd());
    for (Etudiant etudiant : etudiantsNonAffectesTd) {
      GroupeTd groupeTd =
          trouverPlusPetitGroupeTd(this.getAnneeFormation().getListeGroupeTd());
      
      // Vérifier si un groupe a besoin d'être créé
      
      // la valeur de retour est null donc ca veut dire qu'il faut
      // créer un nouveau groupe
      
      if (etudiant.getGroupeTd() == -1) {
        
        if (groupeTd == null) {
          GroupeTd newGroupeTd = new GroupeTd(this.getAnneeFormation());
          newGroupeTd.ajouterEtudiant(etudiant);
        } else {
          groupeTd.ajouterEtudiant(etudiant);
        }
        
      }
    }
    LinkedHashSet<Etudiant> etudiantsNonAffectesTp = getEtudiantNonAffecteTp();
    
    for (Etudiant etudiant : etudiantsNonAffectesTp) {
      GroupeTp groupeTp =
          trouverPlusPetitGroupeTp(this.getAnneeFormation().getListeGroupeTp());
      
      if (etudiant.getGroupeTp() == -1) {
        
        if (groupeTp == null) {
          GroupeTp newGroupeTp = new GroupeTp(this.getAnneeFormation());
          newGroupeTp.ajouterEtudiant(etudiant);
        } else {
          groupeTp.ajouterEtudiant(etudiant);
        }
        
      }
    }
  }
  
  
  /**
   * methode qui cree une liste contenant tous les etudiants qui n'ont pas de
   * groupe.
   *
   * @return liste des etudiants qui n'ont pas de groupe de tp
   */
  public LinkedHashSet<Etudiant> getEtudiantNonAffecteTp() {
    LinkedHashSet<Etudiant> etudiantNonAffecte = new LinkedHashSet<Etudiant>();
    
    for (Etudiant e : anneeFormation.getListeEtudiant()) {
      if (e.getGroupeTp() == -1) {
        etudiantNonAffecte.add(e);
      }
    }
    
    return etudiantNonAffecte;
    
  }
  
  /**
   * methode qui cree une liste contenant tous les etudiants qui n'ont pas de
   * groupe.
   *
   * @return liste des etudiants qui n'ont pas de groupe de td
   */
  
  public LinkedHashSet<Etudiant> getEtudiantNonAffecteTd() {
    LinkedHashSet<Etudiant> etudiantNonAffecte = new LinkedHashSet<>();
    
    for (Etudiant e : anneeFormation.getListeEtudiant()) {
      if (e.getGroupeTd() == -1) {
        etudiantNonAffecte.add(e);
      }
    }
    
    return etudiantNonAffecte;
    
  }
  
  /*
   * // cree des groupes de TD et TP si nécessaire pour qu'ils soient remplis
   * de // maniere homogène sans être pleins public void creerGroupes() { int
   * nombreIdealTd = (anneeFormation.getListeEtudiant().size() /
   * anneeFormation.getTailleMaxTd()) + 1; int nombreIdealTp =
   * (anneeFormation.getListeEtudiant().size() /
   * anneeFormation.getTailleMaxTp()) + 1; int nombreActuelTd =
   * anneeFormation.getListeGroupeTd().size(); int nombreActuelTp =
   * anneeFormation.getListeGroupeTp().size();
   * 
   * while (nombreActuelTd < nombreIdealTd) { anneeFormation.addGroupeTd(new
   * GroupeTd(anneeFormation)); } while (nombreActuelTp < nombreIdealTp) {
   * anneeFormation.addGroupeTp(new GroupeTp(anneeFormation)); } }
   */
  
  // renvoie le groupeTd avec le moins d'etudiants ou null s'ils sont tous
  // pleins
  private GroupeTd trouverPlusPetitGroupeTd(LinkedHashSet<GroupeTd> groupes) {
    // Trouvez le groupe avec la plus petite taille
    GroupeTd groupeAvecPlusPetiteTaille = null;
    int tailleMax = anneeFormation.getTailleMaxTd();
    
    for (GroupeTd groupe : groupes) {
      int taille = groupe.listeEtudiant.size();
      if (taille < tailleMax) {
        tailleMax = taille;
        groupeAvecPlusPetiteTaille = groupe;
      }
    }
    
    return groupeAvecPlusPetiteTaille;
  }
  
  // renvoie le groupeTp avec le moins d'etudiants ou null s'ils sont tous
  // pleins
  private GroupeTp trouverPlusPetitGroupeTp(LinkedHashSet<GroupeTp> groupes) {
    // Trouvez le groupe avec la plus petite taille
    GroupeTp groupeAvecPlusPetiteTaille = null;
    int tailleMin = anneeFormation.getTailleMaxTp();
    
    for (GroupeTp groupe : groupes) {
      int taille = groupe.listeEtudiant.size();
      if (taille < tailleMin) {
        tailleMin = taille;
        groupeAvecPlusPetiteTaille = groupe;
      }
    }
    
    return groupeAvecPlusPetiteTaille;
  }
  
  /**
   * Deplace a la main un etudiant d'un groupe de TD/TP. L'operation peut
   * echouer si les groupes sont deja pleins.
   *
   * @param etudiant l'etudiant a deplacer
   * @param groupeDirige le nouveau groupe de TD (ou 0 si on ne change pas de
   *        groupe de TD)
   * @param groupePratique le nouveau groupe de TP (ou 0 si on ne change de
   *        groupe de TP)
   * @return
   *         <ul>
   *         <li>0 si le ou les deplacements ont ete realises correctement</li>
   *         <li>-1 si le deplacement de TD n'a pas pu etre fait</li>
   *         <li>-2 si le deplacement de TP n'a pas pu etre fait</li>
   *         <lI>-3 si les deplacements de TD et de TP n'ont pas pu etre
   *         faits</li>
   *         </ul>
   */
  
  public int changerGroupe(Etudiant etudiant, int groupeDirige,
      int groupePratique) {
    
    int statusTd = 0;
    int statusTp = 0;
    
    if (groupeDirige > 0
        && groupeDirige <= this.anneeFormation.getListeGroupeTd().size()) {
      
      int currentTd = etudiant.getGroupeTd();
      GroupeTd[] listeTd =
          (GroupeTd[]) this.anneeFormation.getListeGroupeTd().toArray();
      
      listeTd[currentTd - 1].removeEtudiant(etudiant);
      if (listeTd[groupeDirige - 1].ajouterEtudiant(etudiant) == false) {
        statusTd = -1;
      }
    } else {
      statusTd = -1;
    }
    
    
    if (groupePratique > 0
        && groupePratique <= this.anneeFormation.getListeGroupeTp().size()) {
      
      int currentTp = etudiant.getGroupeTp();
      GroupeTp[] listeTp =
          (GroupeTp[]) this.anneeFormation.getListeGroupeTp().toArray();
      
      listeTp[currentTp - 1].removeEtudiant(etudiant);
      if (listeTp[groupePratique - 1].ajouterEtudiant(etudiant) == false) {
        statusTp = -2;
      }
    } else {
      statusTp = -2;
    }
    
    return statusTd + statusTp;
    
  }
  
  
  /**
   * Deplace a la main un etudiant d'un groupe de TD/TP. L'operation peut
   * echouer si les groupes sont deja pleins.
   *
   * @param etudiant l'etudiant a deplacer
   * @param groupeDirige le nouveau groupe de TD (ou 0 si on ne change pas de
   *        groupe de TD)
   * @param groupePratique le nouveau groupe de TP (ou 0 si on ne change de
   *        groupe de TP)
   * @return
   *         <ul>
   *         <li>0 si le ou les deplacements ont ete realises correctement</li>
   *         <li>-1 si le deplacement de TD n'a pas pu etre fait</li>
   *         <li>-2 si le deplacement de TP n'a pas pu etre fait</li>
   *         <lI>-3 si les deplacements de TD et de TP n'ont pas pu etre
   *         faits</li>
   *         </ul>
   */
  public int changerGroupe2(Etudiant etudiant, int groupeDirige,
      int groupePratique) {
    
    int statusTd = 0;
    int statusTp = 0;
    
    
    
    if (groupeDirige > 0
        && groupeDirige <= this.anneeFormation.getListeGroupeTd().size()) {
      
      System.out.println("premier if");
      
      
      int currentTd = etudiant.getGroupeTd();
      GroupeTd cur = this.anneeFormation.getGroupeTd(currentTd);
      GroupeTd newTd = this.anneeFormation.getGroupeTd(groupeDirige);
      
      if (currentTd != groupeDirige) {
        System.out.println("2 if");
        if (newTd.ajouterEtudiant(etudiant) == true) {
          System.out.println("apres ajout");
          
          if (cur != null) {
            System.out.println("Remove");
            cur.removeEtudiant(etudiant);
            
          }
          etudiant.setGroupeTd(groupeDirige);
          
          
        } else {
          statusTd = -1;
        }
      }
      
    } else {
      statusTd = -1;
    }
    
    
    if (groupePratique > 0
        && groupePratique <= this.anneeFormation.getListeGroupeTp().size()) {
      
      int currentTp = etudiant.getGroupeTp();
      GroupeTp curTp = this.anneeFormation.getGroupeTp(currentTp);
      GroupeTp newTp = this.anneeFormation.getGroupeTp(groupePratique);
      
      if (currentTp != groupePratique) {
        if (newTp.ajouterEtudiant(etudiant) == true) {
          if (curTp != null) {
            curTp.removeEtudiant(etudiant);
            
          }
          etudiant.setGroupeTp(groupePratique);
          
        } else {
          statusTp = -2;
        }
      }
      
    } else {
      statusTp = -2;
    }
    
    return statusTd + statusTp;
    
  }
  
  /**
   * Renvoie le nombre de groupes de TD actuellement definis dans la formation.
   *
   * @return nombre de groupes de TD
   */
  public int nombreGroupesTravauxDiriges() {
    return this.anneeFormation.getListeGroupeTd().size();
  }
  
  /**
   * Renvoie le nombre de groupes de TP actuellement definis dans la formation.
   *
   * @return nombre de groupes de TP
   */
  public int nombreGroupesTravauxPratiques() {
    return this.anneeFormation.getListeGroupeTp().size();
  }
  
  
  /**
   * Les etudiants affectes a un certain groupe de TD.
   *
   * @param groupe le groupe de TD
   * @return l'ensemble des etudiants affectes au groupe ou <code>null</code> si
   *         le groupe n'existe pas
   */
  public Set<Etudiant> listeEtudiantsGroupeDirige(int groupe) {
    
    for (GroupeTd g : this.anneeFormation.getListeGroupeTd()) {
      if (g.getNumero() == groupe) {
        Set<Etudiant> liste = g.getListeEtudiant();
        return (liste);
      }
    }
    
    return null;
  }
  
  /**
   * Les etudiants affectes a un certain groupe de TP.
   *
   * @param groupe le groupe de TP
   * @return l'ensemble des etudiants affectes au groupe ou <code>null</code> si
   *         le groupe n'existe pas
   */
  public Set<Etudiant> listeEtudiantsGroupePratique(int groupe) {
    
    for (GroupeTp g : this.anneeFormation.getListeGroupeTp()) {
      if (g.getNumero() == groupe) {
        Set<Etudiant> liste = g.getListeEtudiant();
        return (liste);
      }
    }
    
    return null;
  }
  
  /**
   * Les etudiants inscrits a une certaine option.
   *
   * @param option l'option
   * @return l'ensemble des etudiants inscrits a l'UE ou <code>null</code> si
   *         l'UE n'est pas proposee en option
   */
  public Set<Etudiant> listeEtudiantsOption(UniteEnseignement option) {
    
    for (UniteEnseignement ue : this.anneeFormation.getListeUeOption()) {
      if (ue.equals(option)) {
        return ue.getListeEtudiants();
      }
    }
    
    return null;
  }
  
  
  
}
