package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import formation.AnneeFormation;
import formation.Etudiant;
import formation.Groupe;
import formation.GroupeTd;
import formation.GroupeTp;
import formation.InformationPersonnelle;
import formation.GestionFormation;


import formation.Responsable;
import formation.UeObligatoire;
import formation.UeOptionnelle;
import formation.UniteEnseignement;
import java.util.LinkedHashSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link formation.GestionFormation}.
 *
 * @author Victor Quilgars
 * @see formation.GestionFormation
 */
public class TestGestionFormation {
  
  private GestionFormation G;
  
  /**
   * un premier etudiant
   */
  private Etudiant etu1;
  /**
   * un deuxieme etudiant
   */
  private Etudiant etu2;
  /**
   * un troisieme etudiant
   */
  private Etudiant etu3;
  /**
   * un groupe de td
   */
  private GroupeTd groupeTd1;
  /**
   * un groupe de tp
   */
  private GroupeTp groupeTp1;
  
  /**
   * Instancie la gestion de formation pour les tests.
   *
   * @throws Exception ne peut pas etre levee ici.
   */
  
  @BeforeEach
  void setUp() throws Exception {
    
    G = new GestionFormation();
    
    G.creerFormation("L3IFA", "Victor Quilgars", "vquilgars@icloud.com");
    
    System.out.println(G.getAnneeFormation().toString());
    
    G.setTailleGroupeDirige(2);
    G.setTailleGroupePratique(2);
    
    
    
    InformationPersonnelle info1 =
        new InformationPersonnelle("Quilgars", "Victor", "ok", 20);
    InformationPersonnelle info2 =
        new InformationPersonnelle("Brocard", "Louis", "ok2", 20);
    InformationPersonnelle info3 =
        new InformationPersonnelle("Louarn", "Martin", "ok3", 19);
    
    
    
    etu1 = new Etudiant(info1, "test");
    
    
    etu2 = new Etudiant(info2, "test2");
    
    
    
    etu3 = new Etudiant(info3, "test3");
    
    
    
    LinkedHashSet<Etudiant> listeEtu = new LinkedHashSet<>();
    listeEtu.add(etu1);
    listeEtu.add(etu2);
    listeEtu.add(etu3);
    G.getAnneeFormation().setListeEtudiant(listeEtu);
    
    groupeTd1 = new GroupeTd(G.getAnneeFormation());
    
    
    groupeTp1 = new GroupeTp(G.getAnneeFormation());
    
    
    
  }
  
  /**
   * Ne fait rien apres les tests : a modifier au besoin.
   *
   * @throws Exception ne peut pas etre levee ici
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  
  
  /**
   * test l'attribution automatique des élèves dans les groupes de td et tp.
   */
  @Test
  void testAttributionAuto() {
    
    
    
    G.attribuerAutomatiquementGroupes();
    
    
    
    assertEquals(etu1.getGroupeTd(), 1);
    assertEquals(etu1.getGroupeTp(), 1);
    
    assertEquals(etu2.getGroupeTd(), 1);
    assertEquals(etu2.getGroupeTp(), 1);
    
    assertEquals(etu3.getGroupeTd(), 2);
    assertEquals(etu3.getGroupeTp(), 2);
    
    
    
  }
  
  @Test
  void testChangementGroupes() {
    
    
    
    GroupeTd td4 = new GroupeTd(G.getAnneeFormation());
    GroupeTp tp4 = new GroupeTp(G.getAnneeFormation());
    
    GroupeTd td5 = new GroupeTd(G.getAnneeFormation());
    GroupeTp tp5 = new GroupeTp(G.getAnneeFormation());
    
    GroupeTd td6 = new GroupeTd(G.getAnneeFormation());
    GroupeTp tp6 = new GroupeTp(G.getAnneeFormation());
    
    
    
    System.out.println(G.getAnneeFormation().getListeGroupeTd().size());
    
    for (GroupeTd g : G.getAnneeFormation().getListeGroupeTd()) {
      System.out.println(g.toString());
    }
    
    
    
    int res1 = G.changerGroupe2(etu1, 1, 1);
    
    int res2 = G.changerGroupe2(etu2, 1, 1);
    int res3 = G.changerGroupe2(etu3, 2, 2);
    int res4 = G.changerGroupe2(etu3, 1, 1);
    int res5 = G.changerGroupe2(etu3, 2, 2);

    //int res3 = G.changerGroupe2(etu3, 4, 4);
    //int res4 = G.changerGroupe2(etu3, 4, 3);
    //int res5 = G.changerGroupe2(etu3, 3, 4);
    
    
    assertEquals(res1, 0);
    assertEquals(res2, 0);
    assertEquals(res3, 0);
    assertEquals(res4, -3);
    assertEquals(res5, 0);
    //assertEquals(res2, 0);
    //assertEquals(res3, 0);
    //assertEquals(res4, -2);
    //assertEquals(res5, -1);
    
    // je test si le lors d'un changement de groupe, l'ancien groupe ne contient
    // plus l'etudiant
    
    //assertTrue(
        //G.getAnneeFormation().getGroupeTd(4).getListeEtudiant().contains(etu3));
    
    
    for (GroupeTd g : G.getAnneeFormation().getListeGroupeTd()) {
      System.out.println(g.toString());
    }
    
    
  }
  
  @Test
  void testRemoveEtudiant() {
    System.out.println("tg");
    
    
    groupeTd1.ajouterEtudiant(etu1);
    groupeTd1.ajouterEtudiant(etu2);
    
    assertEquals(groupeTd1.getListeEtudiant().size(), 2);
    
    
    
    groupeTd1.removeEtudiant(etu1);
    
    
  }
}
