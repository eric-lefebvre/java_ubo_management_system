package io;

import formation.AnneeFormation;
import formation.Etudiant;
import formation.GroupeTd;
import formation.GroupeTp;
import formation.UeObligatoire;
import formation.UeOptionnelle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.LinkedHashSet;




/**
 * classe qui implemente l'interface de sauvegrde des donnees de la formation.
 *
 * @author Katia
 */
public class GestionSauvegarde implements InterSauvegarde {
  
  @Override
  public void sauvegarderDonnees(String nomFichier) throws IOException {
    ObjectOutputStream os =
        new ObjectOutputStream(new FileOutputStream(nomFichier));
    os.writeObject(AnneeFormation.instance);
    os.writeObject(AnneeFormation.instance.getListeUeObligatoire());
    os.writeObject(AnneeFormation.instance.getListeUeOption());
    os.writeObject(AnneeFormation.instance.getListeGroupeTd());
    os.writeObject(AnneeFormation.instance.getListeGroupeTp());
    os.writeObject(AnneeFormation.instance.getListeEtudiant());
    
    os.close();
  }
  
  @Override
  public void chargerDonnees(String nomFichier) throws IOException {
    ObjectInputStream is =
        new ObjectInputStream(new FileInputStream(nomFichier));
    try {
      AnneeFormation.instance = (AnneeFormation) is.readObject();
      AnneeFormation.instance
          .setListeUeObligatoire((HashSet<UeObligatoire>) is.readObject());
      AnneeFormation.instance
          .setListeUeOption((HashSet<UeOptionnelle>) is.readObject());
      AnneeFormation.instance
          .setListeGroupeTd((LinkedHashSet<GroupeTd>) is.readObject());
      AnneeFormation.instance
          .setListeGroupeTp((LinkedHashSet<GroupeTp>) is.readObject());
      AnneeFormation.instance
          .setListeEtudiant((LinkedHashSet<Etudiant>) is.readObject());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    
    is.close();
    
  }
  
}
