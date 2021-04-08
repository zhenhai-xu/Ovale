package Model;

import Vue.Affichage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *En héritant du Thread, l'oiseau modifie différentes images pour faire de l'oiseau une animation
 * run() Faire de l'oiseau une animation à travers un Thread,
 * ajouteOiseau() Ajouter les Oisear à la liste.
 * oiseauMove() Déplacez l'oiseau,
 * removeOiseau()Supprimer l'oiseau dans la liste.
 */


public class Oiseau extends Thread {
    /**
    delai qui indique le temps (en millisecondes) entre chaque mise à jour de l’affichage pour l’oiseau,
    état qui permet de savoir dans quelle position est l’oiseau,
    hauteur qui définit la hauteur de l’oiseau dans la fenêtre graphique
    position qui définit l’abscisse.
    counter pour change l'etat a mesure le temps.
    */
    private int delai;
    private Image image;
    private int hauteur;
    private int position;
    private int counter = 8;
    private  int w=100;
    private  int h = 100;
    private Affichage affichage;
    private ArrayList<Oiseau> olist;
    public  Oiseau(Affichage affichage){
        this.delai= new Random().nextInt(100)+100;
        this.position = Affichage.LARG;
        this.hauteur = new Random().nextInt(80)+30;
        this.affichage = affichage;
        this.olist=new ArrayList<Oiseau>();
        //essayer de charger l'image.
        this.image = new ImageIcon("src/Oiseau/0.png").getImage();
    }
    @Override
    public void run() {
        while (!affichage.getEtat().perdu){
        try{
            // le temps qui indique le temps (en millisecondes) entre chaque mise à jour de l’affichage pour l’oiseau,
            Thread.sleep(this.delai);
            //changer etat de l'oiseau
            this.counter++;
            //deplacer l'oiseau vers gauche
            //charger l'image
            olist.forEach(p->p.image = new ImageIcon("src/Oiseau/"+counter % 8+".png").getImage());
            affichage.repaint();
        } catch (Exception e) {
            e.printStackTrace();
            }
        }
    }

    public void ajouteOiseau(Affichage a){
        if(this.counter % 8 < 3) {
            Oiseau oiseau1 = new Oiseau(a);
            this.olist.add(oiseau1);
        }
    }

    public void oiseauMove(){
        olist.forEach(p->p.position-= 30);
    }
    /** remove_Oiseau */
    public void removeOiseau(){
        if(olist.get(0).position<0){
        this.olist.remove(0);
        //System.out.println("remove oiseau");
        }
    }

    /**getEtat*/
    public Image getImg() {
        return image;
    }
    /**getOlist*/
    public ArrayList<Oiseau> getOlist() {
        return olist;
    }

    /**getHauteur*/
    public int getHauteur() {
        return hauteur;
    }
    /**getPosition*/
    public int getPosition() {
        return position;
    }

    /**getW*/
    public int getW(){
        return this.w;
    }

    /**getH*/
    public int getH(){
        return this.h;
    }
}
