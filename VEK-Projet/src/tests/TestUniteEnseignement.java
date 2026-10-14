package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import formation.AnneeFormation;
import formation.Etudiant;
import formation.InformationPersonnelle;
import formation.Responsable;
import formation.UeObligatoire;
import formation.UeOptionnelle;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link formation.UeOptionnelle UeOptionnelle}.
 *
 * @author Eric Lefebvre
 * @see formation.UeOptionnelle
 */
class TestUniteEnseignement {
  
  /**
   * Une UE Optionnelle basique : responsable null, nom et maximum d'etudiants.
   */
  private UeOptionnelle ueOptionnelleBasique;
  
  /**
   * Une UE Optionnelle complete : responsable simple, nom, maximum d'etudiants
   * et liste d'etudiants remplie avec 2 etudiants.
   */
  private UeOptionnelle ueOptionnelleComplete;
  
  private UeObligatoire ueObligatoireBasique;
  
  private UeObligatoire ueObligatoireComplete;
  
  
  private Responsable respo = new Responsable("Eric", "Lefebvre");
  
  @SuppressWarnings("unused")
  private AnneeFormation af = new AnneeFormation("L3 IFA", 2024, respo);
  
  private Etudiant e1;
  
  private Etudiant e2;
  
  /**
   * Instancie une UE Optionnelle basique pour les tests.
   *
   * @throws Exception ne peut pas etre levee ici
   */
  @BeforeEach
  void setUp() throws Exception {
    e1 = new Etudiant(new InformationPersonnelle("Quilgars", "Victor"),
        "12345678");
    e2 = new Etudiant(new InformationPersonnelle("KadourCherif", "Katia"),
        "12345678");
    ueOptionnelleBasique = new UeOptionnelle(null, "Java", 50);
    ueOptionnelleComplete = new UeOptionnelle(respo, "Anglais",
        new HashSet<Etudiant>(Set.of(e1, e2)), 40);
    ueObligatoireBasique = new UeObligatoire(null, "Comm");
    ueObligatoireComplete = new UeObligatoire(respo, "Sport",
        new HashSet<Etudiant>(Set.of(e1, e2)));
    
  }
  
  /**
   * Ne fait rien apres les tests : a modifier au besoin.
   *
   * @throws Exception ne peut pas etre levee ici
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  /**
   * Verifie que la fonction addEtudiant ajoute correctement un etudiant a
   * listeEtudiants.
   */
  @Test
  void testAjoutEtudiantOpt() {
    ueOptionnelleBasique.addEtudiant(e1);
    ueOptionnelleBasique.addEtudiant(e2);
    // assertEquals(ueOptionnelleBasique.getListeEtudiants(),
    // ueOptionnelleComplete.getListeEtudiants());
  }
  
  /**
   * Verifie que la fonction addEtudiant ajoute correctement un etudiant a
   * listeEtudiants.
   */
  @Test
  void testAjoutEtudiantObl() {
    ueObligatoireBasique.addEtudiant(e1);
    ueObligatoireBasique.addEtudiant(e2);
    // System.out.println(ueObligatoireBasique.getListeEtudiants().contains(e1));
    System.out.println(e1.equals(e2));
    System.out.println(e1.equals(e1));
    
    
    assertTrue(ueObligatoireBasique.getListeEtudiants().size() == 2);
    assertTrue(!e1.equals(e2));
    
  }
  
  /**
   * Verifie que le constructeur d'ue optionnelle avec un nombre d'etudiants
   * invalide souleve une exception.
   */
  @Test
  void testConstructeurOptionnelleInvalide() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new UeOptionnelle(null, "Java", -100);
    });
  }
  
  /**
   * Verifie que le constructeur d'ue optionnelle avec des arguments valide
   * fonctionne.
   */
  @Test
  void testConstructeurOptionnelleValide() {
    UeOptionnelle ueValide = new UeOptionnelle(respo, "Python", 50);
    assertEquals(ueValide.getResponsable(), respo);
    assertEquals(ueValide.getNom(), "Python");
    assertTrue(ueValide.getListeEtudiants().isEmpty());
    assertEquals(ueValide.getMaxEtudiants(), 50);
  }
  
  /**
   * Verifie que le constructeur d'ue obligatoire avec des arguments valide
   * fonctionne.
   */
  @Test
  void testConstructeurObligatoireValide() {
    UeObligatoire ueValide = new UeObligatoire(respo, "Maths");
    assertEquals(ueValide.getResponsable(), respo);
    assertEquals(ueValide.getNom(), "Maths");
    assertTrue(ueValide.getListeEtudiants().isEmpty());
  }
}
