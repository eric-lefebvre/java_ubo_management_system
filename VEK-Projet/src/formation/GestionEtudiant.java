package formation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Classe d'implementation de l'interface InterEtudiant qui permet de gerer les
 * donnees de l'etudiant.
 *
 * @author Katia
 *
 */
public class GestionEtudiant implements InterEtudiant {
  static Etudiant etuConnecte = null;
  
  /*
   * ajouter une méthode addNouvEtu dans la classe annee formation pour modifier
   * l'attribut listeEtudint de l'instance globale ? ou voir avec les aures si
   * elle existe déjà voir s'il faut appeler les méthodes d'affectation d'un
   * groupe ??
   */
  
  @Override
  public int inscription(InformationPersonnelle infos, String motDePasse) {
    if (motDePasse.length() >= 8) {
      Etudiant etu = new Etudiant(infos, motDePasse);
      etu.addMessage("Bienvenue parmi nous !");
      return AnneeFormation.instance.addNouvEtu(etu);
    } else {
      return -1;
    }
  }
  
  @Override
  public boolean connexion(int numero, String motDePasse) {
    if (GestionEtudiant.etuConnecte == null) {
      for (Etudiant e : AnneeFormation.instance.getListeEtudiant()) {
        if (e.getNumEtudiant() == numero && e.getMdp().equals(motDePasse)) {
          GestionEtudiant.etuConnecte = e;
          return true;
        }
      }
    } else {
      throw new ConnexionImpossibleException();
    }
    return false;
    
  }
  
  @Override
  public void deconnexion() throws NonConnecteException {
    if (GestionEtudiant.etuConnecte == null) {
      throw new NonConnecteException();
    } else {
      GestionEtudiant.etuConnecte = null;
    }
    
  }
  
  /*
   * problème avec le type de retour : getListeUeObligatoire() retourne un
   * hashet<UeObligatoire>
   * 
   */
  @Override
  public Set<UniteEnseignement> enseignementsObligatoires() {
    Set<UniteEnseignement> l =
        new HashSet<>(AnneeFormation.instance.getListeUeObligatoire());
    return l;
    
  }
  
  @Override
  public Set<UniteEnseignement> enseignementsOptionnels() {
    Set<UniteEnseignement> l =
        new HashSet<>(AnneeFormation.instance.getListeUeOption());
    return l;
  }
  
  /*
   * ne pas oublier d'initialiser l'attribut nombrechoix à -1 dans
   * anneeformation !
   */
  @Override
  public int nombreOptions() throws NonConnecteException {
    if (GestionEtudiant.etuConnecte == null) {
      throw new NonConnecteException();
    } else {
      return AnneeFormation.instance.getNombreChoixOption();
    }
  }
  
  @Override
  public boolean choisirOption(UniteEnseignement ue)
      throws NonConnecteException {
    if (GestionEtudiant.etuConnecte == null) {
      throw new NonConnecteException();
    } else {
      if (GestionEtudiant.etuConnecte.getUeopt()
          .size() < AnneeFormation.instance.getNombreChoixOption()) {
        UeOptionnelle ueOpt = (UeOptionnelle) ue;
        // UE existe
        if (AnneeFormation.instance.getListeUeOption().contains(ueOpt)) {
          // Etudiant n'a pas ue et ue a place
          if (ueOpt.getListeEtudiants().size() < ueOpt.getMaxEtudiants()
              && !GestionEtudiant.etuConnecte.getUeopt().contains(ueOpt)) {
            boolean succes = ueOpt.addEtudiant(etuConnecte);
            if (succes) {
              GestionEtudiant.etuConnecte.addUeOpt(ueOpt);
              GestionEtudiant.etuConnecte
                  .addMessage("Vous avez bien été inscrit à l'ue : " + ue.nom);
              return true;
            } else {
              return false;
            }
          }
        }
      }
      return false;
    }
  }
  
  @Override
  public int getNumeroGroupeTravauxDiriges() throws NonConnecteException {
    if (GestionEtudiant.etuConnecte == null) {
      throw new NonConnecteException();
    } else {
      return GestionEtudiant.etuConnecte.getGroupeTd();
    }
  }
  
  @Override
  public int getNumeroGroupeTravauxPratiques() throws NonConnecteException {
    if (GestionEtudiant.etuConnecte == null) {
      throw new NonConnecteException();
    } else {
      return GestionEtudiant.etuConnecte.getGroupeTp();
    }
  }
  
  @Override
  public Set<UniteEnseignement> enseignementsSuivis()
      throws NonConnecteException {
    if (GestionEtudiant.etuConnecte == null) {
      throw new NonConnecteException();
    } else {
      Set<UniteEnseignement> tot = new HashSet<>();
      tot.addAll(GestionEtudiant.etuConnecte.getUes());
      tot.addAll(GestionEtudiant.etuConnecte.getUeopt());
      return tot;
    }
  }
  
  @Override
  public List<String> listeTousMessages() throws NonConnecteException {
    if (GestionEtudiant.etuConnecte == null) {
      throw new NonConnecteException();
    } else {
      return GestionEtudiant.etuConnecte.messagesTous;
    }
  }
  
  @Override
  public List<String> listeMessageNonLus() throws NonConnecteException {
    if (GestionEtudiant.etuConnecte == null) {
      throw new NonConnecteException();
    } else {
      return GestionEtudiant.etuConnecte.messagesNonLus;
    }
  }
  
  /**
   * méthode qui "lit" un message.
   * Elle enlève le message passé en param de la liste des msg non lus
   * 
   */
  public void lireMessage(String msg) throws NonConnecteException {
    if (GestionEtudiant.etuConnecte == null) {
      throw new NonConnecteException();
    } else {
      GestionEtudiant.etuConnecte.messagesNonLus.remove(msg);
    }
  }
  
  @Override
  public boolean inscriptionFinalisee() throws NonConnecteException {
    if (GestionEtudiant.etuConnecte == null) {
      throw new NonConnecteException();
    } else {
      if (GestionEtudiant.etuConnecte.getUeopt()
          .size() < AnneeFormation.instance.getNombreChoixOption()
          || GestionEtudiant.etuConnecte.getGroupeTd() == -1
          || GestionEtudiant.etuConnecte.getGroupeTp() == -1) {
        return false;
      }
      return true;
    }
  }
  
}
