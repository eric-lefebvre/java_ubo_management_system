package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import formation.InformationPersonnelle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link formation.InformationPersonnelle
 * InformationPersonnelle}.
 *
 * @author Eric Cariou
 * @see formation.InformationPersonnelle
 */
class TestInformationPersonnelle {
  
  /**
   * Une information basique : prenom et nom.
   */
  private InformationPersonnelle infoBasique;
  /**
   * Une information complete : prenom, nom, adresse et age.
   */
  private InformationPersonnelle infoComplete;
  
  /**
   * Instancie une information basique et une complete pour les tests.
   *
   * @throws Exception ne peut pas etre levee ici
   */
  @BeforeEach
  void setUp() throws Exception {
    infoBasique = new InformationPersonnelle("Skywalker", "Luke");
    infoComplete =
        new InformationPersonnelle("Skywalker", "Luke", "Planete Tatooine", 20);
  }
  
  /**
   * Ne fait rien apres les tests : a modifier au besoin.
   *
   * @throws Exception ne peut pas etre levee ici
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  /**
   * Verifie que l'on peut positionner un age de 25 ans.
   */
  @Test
  void testAge25Basique() {
    infoBasique.setAge(25);
    assertEquals(infoBasique.getAge(), 25);
  }
  
  /**
   * Verifie qu'on ne peut pas positionner un age negatif sur une information
   * basique.
   */
  @Test
  void testAgeNegatifBasique() {
    infoBasique.setAge(-20);
    assertTrue(infoBasique.getAge() != -20);
  }
  
  /**
   * Verifie qu'on ne peut pas positionner un age negatif sur une information
   * complete : l'age reste le meme qu'avant.
   */
  @Test
  void testAgeNegatifComplet() {
    int age = infoComplete.getAge();
    infoComplete.setAge(-20);
    assertEquals(infoComplete.getAge(), age);
  }
  
  
  /**
   * Verifie qu'une adresse n'est pas null quand on cree une information
   * personnelle.
   */
  @Test
  void testAdresseNonNull() {
    assertTrue(infoBasique.getAdresse() != null);
    assertTrue(infoComplete.getAdresse() != null);
  }
  
  /**
   * Verifie qu'on ne peut pas positionner une adresse null sur une information
   * existante.
   */
  @Test
  void testSetterAdresseNull() {
    infoComplete.setAdresse(null);
    assertTrue(infoComplete.getAdresse() != null);
  }
  
  /**
   * Verifie que les parametres des constructeurs sont correctement geres.
   */
  @Test
  void testConstructeur() {
    InformationPersonnelle inf =
        new InformationPersonnelle("Vador", "Dark", null, -30);
    assertEquals(inf.getNom(), "Vador");
    assertEquals(inf.getPrenom(), "Dark");
    assertTrue(inf.getAdresse() != null);
    assertTrue(inf.getAge() >= 0);
  }
  
}
