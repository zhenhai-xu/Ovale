import Control.Control;
import Control.Voler;
import Model.Parcours;
import Model.Etat;
import Vue.Affichage;
import Control.Avancer;

import java.io.IOException;

import javax.swing.JFrame;
/**Class Main
 * Class de lancement de jeu.
 * java JDK11.
 * */
public class Main  {
    public static void main(String[] args) throws IOException {
        //initialisation
        Parcours parcours = new Parcours();
        Etat etat= new Etat(parcours);
        Affichage affichage = new Affichage(etat);
        Control control = new Control(etat,affichage);

        // Un thread qui permet  maintient l'oval vers le bas
        Voler voler = new Voler(etat,affichage,parcours);

        // Un thread qui permet de d�place le segment de ligne compl�tement vers la gauche
        Thread avancer = new Thread(new Avancer(etat,affichage));

        //Transformez les images d'oiseaux en animation
        affichage.oiseau.start();

        //D�marrer le Thread.
        voler.start();
        avancer.start();

        //Commencer le jeu!
        JFrame frame = new JFrame("Vole, vole, petit oiseau!");
        //D�finir le mode d'arr�t.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Ajouter l'affichage � la frame.
        frame.add(affichage);
        //Ajouter un moniteur de souris.
        frame.addMouseListener(control);
        //Laisser le cadre ajuster automatiquement sa taille.
        frame.pack();
        //R�glez l'�cran pour qu'il soit visible.
        frame.setVisible(true);
    }

}

