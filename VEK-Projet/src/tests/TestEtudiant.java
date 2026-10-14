package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import formation.AnneeFormation;
import formation.InformationPersonnelle;
import formation.Etudiant;
import formation.GroupeTd;
import formation.GroupeTp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link formation.Etudiant Etudiant}.
 *
 * @author Eric Cariou
 * @see formation.Etudiant
 */
class TestEtudiant {
  
  private AnneeFormation formation = new AnneeFormation("test", 2024, null, 2, 2, 2);
  
  /**
   * Un etudiant avec ses informations basiques : prenom et nom.
   */
  private Etudiant etudiantInfoBasique;
  /**
   * Un étudiant avec ses informations completes : prenom, nom, adresse et age.
   */
  private Etudiant etudiantInfoComplete;
  
  /**
   * Instancie une information basique et une complete pour les tests.
   *
   * @throws Exception ne peut pas etre levee ici
   */
  @BeforeEach
  void setUp() throws Exception {
    
    
    
    InformationPersonnelle basique =
        new InformationPersonnelle("Gallagher", "Fiona");
    InformationPersonnelle complete =
        new InformationPersonnelle("Marshall", "Kevin", "rue de la paix", 21);
    
    etudiantInfoBasique = new Etudiant(basique, "mdp123");
    etudiantInfoComplete = new Etudiant(complete, "mdp456");
  }
  
  /**
   * Ne fait rien apres les tests : a modifier au besoin.
   *
   * @throws Exception ne peut pas être levee ici
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  @Test
  void testNumEtudiant() {
    assertTrue(etudiantInfoBasique.getNumEtudiant() != 0);
    assertTrue(etudiantInfoComplete.getNumEtudiant() != 0);
    assertTrue(etudiantInfoBasique.getNumEtudiant() != etudiantInfoComplete
        .getNumEtudiant());
  }
  
  @Test
  void testToString() {
    System.out.println(etudiantInfoBasique.toString());
    System.out.println(etudiantInfoComplete.toString());
  }
  
  /**
   * Verifie que les parametres des constructeurs sont correctement geres.
   */
  @Test
  void testConstructeur() {
    InformationPersonnelle info =
        new InformationPersonnelle("Maroon", "Christine");
    Etudiant etu = new Etudiant(info, "jouer34");
    assertEquals(etu.getMdp(), "jouer34");
    assertTrue(etu.getGroupeTd() == -1);
    assertTrue(etu.getGroupeTp() == -1);
    assertTrue(etu.getUes() != null);
    assertTrue(etu.getUeopt().size() == 0);
  }
  
  @Test
  void testGetNumGroupeTdTp() {
    GroupeTd td1 = new GroupeTd(formation);
    GroupeTp tp1 = new GroupeTp(formation);
    InformationPersonnelle info =
        new InformationPersonnelle("Maroon", "Christine");
    Etudiant etu = new Etudiant(info, "jouer34");
    
    td1.ajouterEtudiant(etu);
    tp1.ajouterEtudiant(etu);
    
    assertTrue(etu.getNumGroupTd(td1) == 1);
    assertTrue(etu.getNumGroupTp(tp1) == 1);
    
    
  }
  
}
