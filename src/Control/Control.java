package Control;
import Model.Etat;
import Vue.Affichage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**Class Control
 * Utilisez le moniteur de la souris
 * pour faire monter l'ovale
 * après avoir cliqué sur la souris.
 */
 public class  Control implements MouseListener {
     private Etat etat;
     private Affichage affichage;

     //constructeur de contrôle
     public Control(Etat etat, Affichage affichage) {
         this.etat = etat;
         this.affichage = affichage;
     }

     /*Cliquez une fois sur la souris pour exécuter la fonction de
     saut et modifier la coordonnée de l'axe Y*/
     @Override
     public void mouseClicked(MouseEvent e) {
         //si joueur a perdu, l’interface ne réagisse plus aux clics souris ;
         if (etat.testPerdu()){
             affichage.removeMouseListener(this);
         //Juste pour tester, vérifiez la valeur de l'axe Y
         //System.out.println(etat.getHauteur());
        }else {
             etat.jump();
             affichage.repaint();
         }
         //Après avoir modifié les coordonnées de l'axe Y, redessinez une fois，


     }
     @Override
     public void mousePressed(MouseEvent e) { }
     @Override
     public void mouseReleased(MouseEvent e) {}
     @Override
     public void mouseEntered(MouseEvent e) {}
     @Override
     public void mouseExited(MouseEvent e) {}
 }