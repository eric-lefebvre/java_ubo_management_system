package formation;

import java.util.List;
import java.util.Set;

/**
 * Les fonctionnalites offertes a un etudiant.
 *
 * @author Eric Cariou
 */
public interface InterEtudiant {
  
  /**
   * Cree le compte d'un etudiant a partir de ses informations personnelles et
   * de son mot de passe puis retourne son numero d'etudiant genere
   * automatiquement.
   *
   * @param infos les informations personnelles de l'etudiant
   * @param motDePasse le mot de passe de l'etudiant pour se connecter (la
   *        chaine doit etre non vide)
   * @return le numero unique de l'etudiant ou -1 en cas de probleme
   */
  int inscription(InformationPersonnelle infos, String motDePasse);
  
  /**
   * Connecte l'etudiant avec son numero d'etudiant et son mot de passe.
   *
   * @param numero le numero de l'etudiant
   * @param motDePasse le mot de passe de l'etudiant
   * @return <code>true</code> si le couple numero/mot de passe est correct
   *         (l'etudiant est alors considere comme connecte au systeme),
   *         <code>false</code> si le couple est incorrect
   */
  boolean connexion(int numero, String motDePasse);
  
  /**
   * Deconnecte l'etudiant actuellement connecte au systeme.
   *
   * @throws NonConnecteException si aucun etudiant n'etait connecte
   */
  void deconnexion() throws NonConnecteException;
  
  /**
   * L'ensemble des unites d'enseignement obligatoires de l'annee de formation.
   *
   * @return l'ensemble des UE obligatoires
   */
  Set<UniteEnseignement> enseignementsObligatoires();
  
  /**
   * L'ensemble des unites d'enseignement optionnelles de l'annee de formation.
   *
   * @return l'ensemble des UE optionnelles
   */
  Set<UniteEnseignement> enseignementsOptionnels();
  
  /**
   * Retourne le nombre d'options que l'etudiant doit choisir au total.
   *
   * @return le nombre d'options que l'etudiant doit choisir ou -1 si ce nombre
   *         n'a pas ete encore defini.
   * @throws NonConnecteException si la methode est appelee alors que l'etudiant
   *         n'est pas connecte
   */
  int nombreOptions() throws NonConnecteException;
  
  /**
   * Choix d'une UE optionnelle par l'etudiant.
   *
   * @param ue l'UE que l'etudiant veut choisir
   * @return <code>true</code> si l'etudiant a ete inscrit a l'UE,
   *         <code>false</code> si l'inscription n'a pas pu se faire (manque de
   *         places dans l'UE ou l'UE n'est pas une option)
   * @throws NonConnecteException si la methode est appelee alors que l'etudiant
   *         n'est pas connecte
   */
  boolean choisirOption(UniteEnseignement ue) throws NonConnecteException;
  
  
  /**
   * Renvoie le numero de groupe de TD de l'etudiant s'il a ete defini.
   *
   * @return le numero de groupe de TD s'il a ete defini ou -1 si ca n'est pas
   *         encore le cas
   * @throws NonConnecteException si la methode est appelee alors que l'etudiant
   *         n'est pas connecte
   */
  int getNumeroGroupeTravauxDiriges() throws NonConnecteException;
  
  /**
   * Renvoie le numero de groupe de TP de l'etudiant s'il a ete defini.
   *
   * @return le numero de groupe de TP s'il a ete defini ou -1 si ca n'est pas
   *         encore le cas
   * @throws NonConnecteException si la methode est appelee alors que l'etudiant
   *         n'est pas connecte
   */
  int getNumeroGroupeTravauxPratiques() throws NonConnecteException;
  
  /**
   * Renvoie l'ensemble des enseignements suivis par l'etudiant : les UE
   * obligatoires ainsi que les UE optionnelles ou il est inscrit.
   *
   * @return l'ensemble des UE suivies par l'etudiant
   * @throws NonConnecteException si la methode est appelee alors que l'etudiant
   *         n'est pas connecte
   */
  Set<UniteEnseignement> enseignementsSuivis() throws NonConnecteException;
  
  /**
   * Renvoie la liste de tous les messages recus par l'etudiant (lus et non
   * lus), dans l'ordre ou ils ont ete recus.
   *
   * @return tous les messages de l'etudiant
   * @throws NonConnecteException si la methode est appelee alors que l'etudiant
   *         n'est pas connecte
   */
  List<String> listeTousMessages() throws NonConnecteException;
  
  /**
   * Renvoie la liste des messages non lus par l'etudiant, dans l'ordre ou ils
   * ont ete recus.
   *
   * @return les messages non lus de l'etudiant
   * @throws NonConnecteException si la methode est appelee alors que l'�tudiant
   *         n'est pas connecte
   */
  List<String> listeMessageNonLus() throws NonConnecteException;
  
  /**
   * Indique si l'inscription de l'etudiant est finalisee, c'est-a-dire si
   * l'etudiant :
   * <ul>
   * <li>A ete affecte a un groupe de TD</li>
   * <li>A ete affecte a un groupe de TP</li>
   * <li>A choisi autant d'options que requis</li>
   * </ul>
   * Si au moins une des conditions n'est pas validee, l'etudiant n'a pas
   * finalise son inscription.
   *
   * @return <code>true</code> si l'inscription de l'etudiant est finalisee,
   *         <code>false</code> sinon
   * @throws NonConnecteException si la methode est appelee alors que l'etudiant
   *         n'est pas connecte
   */
  boolean inscriptionFinalisee() throws NonConnecteException;
  
}

