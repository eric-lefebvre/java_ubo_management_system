package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import formation.Responsable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link formation.Responsable}.
 *
 * @author Victor Quilgars
 * @see formation.Responsable
 */
class TestResponsable {
  
  /**
   * Un responsable.
   */
  private Responsable responsable1;
  
  
  
  /**
   * Instancie un responsable pour les tests.
   *
   * @throws Exception ne peut pas etre levee ici.
   */
  @BeforeEach
  void setUp() throws Exception {
    responsable1 = new Responsable("Victor", "Quilgars",
        "victor.quilgars@etudiant.univ-brest.fr");
  }
  
  /**
   * Ne fait rien apres les tests : a modifier au besoin.
   *
   * @throws Exception ne peut pas etre levee ici
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  /**
   * Verifie que l'on peut positionner une adresse mail test@test.fr.
   */
  @Test
  void testEmail() {
    responsable1.setEmail("test@test.fr");
    assertEquals(responsable1.getEmail(), "test@test.fr");
  }
  
  /**
   * Verifie que les parametres des constructeurs sont correctement geres.
   */
  @Test
  void testConstructeur() {
    Responsable responsable =
        new Responsable("Vador", "Dark", "dark.vador@test.fr");
    assertEquals(responsable.getNom(), "Vador");
    assertEquals(responsable.getPrenom(), "Dark");
    assertEquals(responsable.getEmail(), "dark.vador@test.fr");
    
    
    
  }
  
  /**
   * Verifie que les parametres des constructeurs sont correctement geres.
   */
  @Test
  void testConstructeur2() {
    
    Responsable responsable2 = new Responsable("Eric", "Lefebvre");
    assertEquals(responsable2.getNom(), "Eric");
    assertEquals(responsable2.getPrenom(), "Lefebvre");
    assertEquals(responsable2.getEmail(), "");
    
  }
  
  /**
   * Verifie que les parametres des constructeurs sont correctement geres.
   */
  @Test
  void testConstructeur3() {
    
    Responsable responsable3 = new Responsable();
    assertEquals(responsable3.getNom(), "Eric");
    assertEquals(responsable3.getPrenom(), "Cariou");
    assertEquals(responsable3.getEmail(), "eric.cariou@univ-brest.fr");
    
  }
  
  
}
