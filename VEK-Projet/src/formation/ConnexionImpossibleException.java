package formation;

/**
 * Exception levee quand l'etudiant essaye de se connecter alors qu'un session
 * de connexion est déjà ouverte ( le système ne gère qu'un seul étudiant
 * connecté à la fois).
 *
 * @author Katia
 */
public class ConnexionImpossibleException extends RuntimeException {
  
  /**
   * Identifiant de serialisation par défaut.
   */
  private static final long serialVersionUID = 2795203909319304032L;
  
}
