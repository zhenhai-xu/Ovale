package Control;

import Model.Etat;
import Model.Parcours;
import Vue.Affichage;

import javax.swing.*;
import java.util.Random;

/**Class Voler
 * Descente automatique de l'ovale,
 * et suppression des points de la piste,
 * Et observez l'état final du jeu.
 */
public class Voler extends Thread{
    private Etat etat;
    private Affichage affichage;
    private Parcours parcours;
    public static final Random rand = new Random();
    public Voler(Etat etat, Affichage affichage, Parcours parcours){
        this.etat=etat;
        this.affichage=affichage;
        this.parcours=parcours;
    }

    @Override
    public void run() {
        while (!etat.perdu) {
            try {
                Thread.sleep(200);
                etat.moveDown();
                etat.perdu=etat.testPerdu();
                parcours.removeParcours();
                affichage.revalidate();
                affichage.repaint();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        JOptionPane.showMessageDialog(null, "votre score est de : " + etat.getParcours().getPosition() );
    }
}
