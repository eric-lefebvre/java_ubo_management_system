package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import formation.AnneeFormation;
import formation.InformationPersonnelle;
import formation.Etudiant;
import formation.GroupeTd;
import formation.Responsable;
import formation.UeObligatoire;
import formation.UeOptionnelle;
import formation.UniteEnseignement;
import java.util.LinkedHashSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link formation.AnneeFormation}.
 *
 * @author Victor Quilgars
 * @see formation.AnneeFormation
 */
public class TestAnneeFormation {
  
  /**
   * Une annee de formation.
   */
  private AnneeFormation anneeFormation;
  
  /**
   * Le responsable de la formation
   */
  
  private Responsable responsable;
  
  /**
   * un autre responsable pour les tests.
   */
  private Responsable resp2;
  
  private LinkedHashSet<UeOptionnelle> listeOption;
  
  private LinkedHashSet<UeObligatoire> listeObligatoire;
  
  /**
   * Instancie unne annee de formation pour les tests.
   *
   * @throws Exception ne peut pas etre levee ici.
   */
  
  @BeforeEach
  void setUp() throws Exception {
    responsable = new Responsable("Victor", "Quilgars", "vquilgars@icloud.com");
    
    resp2 = new Responsable();
    
    anneeFormation = new AnneeFormation("L3IFA", 2023, responsable, 30, 15, 2);
    
    
  }
  
  /**
   * Ne fait rien apres les tests : a modifier au besoin.
   *
   * @throws Exception ne peut pas etre levee ici
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  /**
   * Verifie que l'on peut positionner un responsable dans la formation
   */
  
  @Test
  void testSetterResponsable() {
    anneeFormation.setResponsable(resp2);
    assertEquals(anneeFormation.getResponsable(), resp2);
  }
  
  /**
   * Verifie que l'on peut positionner un nom deans la formation.
   */
  
  @Test
  void testSetterNom() {
    anneeFormation.setNom("L3test");
    assertEquals(anneeFormation.getNom(), "L3test");
  }
  
  /**
   * Verifie que l'on peut positionner un nombre de choix d'option.
   */
  
  @Test
  void testSetterChoixOption() {
    anneeFormation.setNombreChoixOption(3);
    assertEquals(anneeFormation.getNombreChoixOption(), 3);
  }
  
  /**
   * Verifie que les paramatres des constructeurs sont correctement geres.
   */
  @Test
  void testConstructeur() {
    
    AnneeFormation annee =
        new AnneeFormation("L3", 2020, responsable, 20, 10, 4);
    
    assertEquals(annee.getNom(), "L3");
    assertEquals(annee.getAnnee(), 2020);
    assertEquals(annee.getTailleMaxTd(), 20);
    assertEquals(annee.getTailleMaxTp(), 10);
    assertEquals(annee.getNombreChoixOption(), 4);
    
    
    
  }
  
  /**
   * Verifie que l'on peut recuperer l'annee de la formation.
   */
  
  @Test
  void testGetterAnnee() {
    assertEquals(anneeFormation.getAnnee(), 2023);
  }
  
  /**
   * Verifie que l'on peut recuperer la aille des groupes.
   */
  
  @Test
  void testGetterTailleGroupe() {
    assertEquals(anneeFormation.getTailleMaxTd(), 30);
    assertEquals(anneeFormation.getTailleMaxTp(), 15);
  }
  
  /**
   * Verifie que le setter dd'une liste d'ue optionelles fonctionne.
   */
  
  @Test
  void testSetterUeOption() {
    UeOptionnelle option = new UeOptionnelle(responsable, "option1", null, 10);
    UeOptionnelle option2 = new UeOptionnelle(responsable, "option2", null, 10);
    
    LinkedHashSet<UeOptionnelle> liste1 = new LinkedHashSet<>();
    LinkedHashSet<UeOptionnelle> liste2 = new LinkedHashSet<>();
    
    liste1.add(option);
    liste1.add(option2);
    liste2.add(option);
    liste2.add(option2);
    
    anneeFormation.setListeUeOption(liste1);
    assertEquals(anneeFormation.getListeUeOption(), liste2);
    
    
    
  }
  
  /**
   * Verifie que le setter dd'une liste d'ue optionelles fonctionne.
   */
  
  @Test
  void testSetterUeObligatoire() {
    UeObligatoire option = new UeObligatoire(responsable, "option1", null);
    UeObligatoire option2 = new UeObligatoire(responsable, "option2", null);
    
    LinkedHashSet<UeObligatoire> liste1 = new LinkedHashSet<>();
    LinkedHashSet<UeObligatoire> liste2 = new LinkedHashSet<>();
    
    liste1.add(option);
    liste1.add(option2);
    liste2.add(option);
    liste2.add(option2);
    
    anneeFormation.setListeUeObligatoire(liste1);
    assertEquals(anneeFormation.getListeUeObligatoire(), liste2);
    
    
    
  }
  
  
  
  
}
