package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import formation.AnneeFormation;
import formation.Groupe;
import formation.GroupeTd;
import formation.GroupeTp;
import formation.Responsable;
import formation.UeOptionnelle;
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
class TestGroupe {
  
  /**
   * Instancie une UE Optionnelle basique pour les tests.
   *
   * @throws Exception ne peut pas etre levee ici
   */
  @BeforeEach
  void setUp() throws Exception {
    
  }
  
  /**
   * Ne fait rien apres les tests : a modifier au besoin.
   *
   * @throws Exception ne peut pas etre levee ici
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  /**
   * Verifie que le constructeur d'un groupe de td avec des arguments valide
   * fonctionne.
   */
  @Test
  void testConstructeurTd() {
    AnneeFormation af = new AnneeFormation("Formation", 2023, null, 30, 25, 3);
    GroupeTd td1 = new GroupeTd(af);
    GroupeTd td2 = new GroupeTd(af);
    assertEquals(td1.getNumero(), 1);
    assertEquals(td2.getNumero(), 2);
    assertEquals(td1.getTailleMax(), 30);
  }
  
  /**
   * Verifie que le constructeur d'un groupe de tp avec des arguments valide
   * fonctionne.
   */
  @Test
  void testConstructeurTp() {
    AnneeFormation af = new AnneeFormation("Formation", 2023, null, 30, 25, 3);
    GroupeTp tp1 = new GroupeTp(af);
    GroupeTp tp2 = new GroupeTp(af);
    assertEquals(tp1.getNumero(), 1);
    assertEquals(tp2.getNumero(), 2);
    assertEquals(tp1.getTailleMax(), 25);
  }
}
