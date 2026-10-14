package formation;

import java.util.Set;

/**
 * Les services de gestion d'une annee de formation.
 *
 * @author Eric Cariou
 *
 */
public interface InterGestionFormation {
  
  /**
   * Cree une (annee de) formation avec son nom et celui du responsable. Si une
   * formation existait deje dans le systeme, la nouvelle la remplace et efface
   * la precedente.
   *
   * @param nomFormation le nom de la formation (chaine non vide)
   * @param nomResponsable le nom et prenom du responsable (chaine non vide)
   * @param email l'email du responsable (adresse email valide)
   */
  void creerFormation(String nomFormation, String nomResponsable, String email);
  
  /**
   * Renvoie le nom du responsable de formation.
   *
   * @return le nom du responsable de formation ou <code>null</code> s'il n'a
   *         pas ete defini
   */
  String getNomResponsableFormation();
  
  /**
   * Renvoie l'adresse email du responsable de formation.
   *
   * @return l'adresse email du responsable de formation ou <code>null</code> si
   *         elle n'a pas ete definie
   */
  String getEmailResponsableFormation();
  
  /**
   * Renvoie le nom de la formation.
   *
   * @return le nom de la formation
   */
  String getNomFormation();
  
  
  /**
   * Rajoute une UE obligatoire a la formation. L'UE ne doit pas deja etre dans
   * la liste des UE de la formation (ni en obligatoire, ni en optionnel).
   *
   * @param ue l'UE a rajouter
   * @return <code>true</code> si l'ajout a ete fait, <code>false</code> en cas
   *         de probleme
   */
  boolean ajouterEnseignementObligatoire(UniteEnseignement ue);
  
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
  boolean ajouterEnseignementOptionnel(UniteEnseignement ue, int nbPlaces);
  
  /**
   * Definit le nombre d'options que doit choisir un etudiant. Ne peut plus etre
   * modifie une fois defini.
   *
   * @param nombre le nombre d'options e choisir pour un etudiant (nombre
   *        superieur ou egal a 1)
   */
  void definirNombreOptions(int nombre);
  
  /**
   * Definit le nombre de places dans un groupe de TD. Ne peut plus etre modifie
   * une fois defini.
   *
   * @param taille le nombre de place dans un groupe de TD (nombre superieur a
   *        1)
   */
  void setTailleGroupeDirige(int taille);
  
  /**
   * Definit le nombre de places dans un groupe de TP. Ne peut plus etre modifie
   * une fois defini.
   *
   * @param taille le nombre de place dans un groupe de TP (nombre superieur a
   *        1)
   */
  void setTailleGroupePratique(int taille);
  
  /**
   * Renvoie le nombre de places dans un groupe de TD.
   *
   * @return le nombre de places dans un groupe de TD ou -1 s'il n'a pas encore
   *         ete defini
   */
  int getTailleGroupeDirige();
  
  /**
   * Renvoie le nombre de places dans un groupe de TP.
   *
   * @return le nombre de places dans un groupe de TP ou -1 s'il n'a pas encore
   *         ete defini
   */
  int getTailleGroupePratique();
  
  /**
   * Attribue automatiquement les etudiants non encore affectes e des groupes de
   * TD et de TP. Au besoin, cree de nouveaux groupes de TD ou de TP. Pour
   * harmoniser la taille des groupes, des etudiants deja places peuvent etre
   * deplaces. Les etudiants concernes par une affectation ou un changement
   * d'affectation recoivent un message pour leur preciser ce qu'il s'est passe.
   */
  void attribuerAutomatiquementGroupes();
  
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
  int changerGroupe(Etudiant etudiant, int groupeDirige, int groupePratique);
  
  /**
   * Renvoie le nombre de groupes de TD actuellement definis dans la formation.
   *
   * @return nombre de groupes de TD
   */
  int nombreGroupesTravauxDiriges();
  
  /**
   * Renvoie le nombre de groupes de TP actuellement definis dans la formation.
   *
   * @return nombre de groupes de TP
   */
  int nombreGroupesTravauxPratiques();
  
  
  /**
   * Les etudiants affectes a un certain groupe de TD.
   *
   * @param groupe le groupe de TD
   * @return l'ensemble des etudiants affectes au groupe ou <code>null</code> si
   *         le groupe n'existe pas
   */
  Set<Etudiant> listeEtudiantsGroupeDirige(int groupe);
  
  /**
   * Les etudiants affectes a un certain groupe de TP.
   *
   * @param groupe le groupe de TP
   * @return l'ensemble des etudiants affectes au groupe ou <code>null</code> si
   *         le groupe n'existe pas
   */
  Set<Etudiant> listeEtudiantsGroupePratique(int groupe);
  
  /**
   * Les etudiants inscrits a une certaine option.
   *
   * @param option l'option
   * @return l'ensemble des etudiants inscrits a l'UE ou <code>null</code> si
   *         l'UE n'est pas proposee en option
   */
  Set<Etudiant> listeEtudiantsOption(UniteEnseignement option);
}
