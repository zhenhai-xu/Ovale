package Model;
import Vue.Affichage;

import java.awt.*;
import java.util.ArrayList;

/** Classe Etat
 * V�rifiez l'�tat de l'ovale.
 * jump () fait monter l'ovale,
 * moveDown () fait descendre l'ovale,
 * testPerdu()d�tecter si l'ellipse et la ligne droite se heurtent
 */
public class Etat {
    /**La valeur des pixels de jump  */
    public static final int SAUT = 35;
    /**Valeur de pixel d�croissante */
    public static final int DESC = 2;
    private Parcours parcours;
    private int hauteur;
    public boolean perdu = false;

    /**
     * Constructeur
     * @param parcours
     */
    public Etat(Parcours parcours){
        this.hauteur= Affichage.CentreY;
        this.parcours= parcours;
    }

    /**Obtenir le hauteur actuel de l'ovale*/
    public int getHauteur(){
        return this.hauteur;
    }

    /**Obtenir Parcours*/
    public Parcours getParcours(){
        return this.parcours;
    }

    /**Changement l'hauteur de l'ovale quand on clic le souris*/
    public void jump() {
        if (this.hauteur > 10) {
            //L'objet continue de sauter.
            // Modifier les coordonn�es de l'axe Y.
            this.hauteur -= SAUT;
        }
    }

    /**Modifiez la hauteur de l'oval pour faire baisser sa hauteur*/
    public void moveDown (){
        if(this.hauteur < Affichage.HAUT - Affichage.HEIGHT) {
            //L'objet continue de moveDown.
            // Modifier les coordonn�es de l'axe Y.
            this.hauteur += DESC;
        }
            //Si l'objet est � l'endroit le plus base, il ne peut pas continuer � decendre.
    }

    /**D�tection de collision, pour d�tecter si le jeu est termin�*/
    public boolean testPerdu(){
        //x est l'axe x du centre de l'ovale.
        double x = Affichage.WIDTH/2+Affichage.CentreX;
        //h est l'�paisseur du ligne breisee,
        //Divisez par deux pour les tests de collision.
        double h = Affichage.Stroke /2;
        //x1,x2,y1,y2,les parametre pour de la formule pour calculer la pente.
        double x1=0.0, x2=0.0 ,y1=0.0, y2 = 0.0;
        /*
        line est la hauteur sur l'axe y de l'intersection de la ligne calcul�e et de l'ovale.
        k est la pente
        b est le montant de la translation de la ligne brisee.
        a est l'angle de la ligne horizontale apr�s l'inclinaison de la ligne breisee.
        */
        double line = 0;double k = 0;double b = 0;

        ArrayList<Point> pa = parcours.ajoutePoint();
        /*Trouvez les deux points actuels de la ligne breisee*/
        for(int i=0 ; i < pa.size()-1;i++) {
            if (x >= pa.get(i).x && x <= pa.get(i + 1).x) {
                x1 = pa.get(i).x;
                x2 = pa.get(i + 1).x;
                y1 = pa.get(i).y;
                y2 = pa.get(i + 1).y;
            }
        }
        //Appliquer des formules math�matiques,Calculer les valeurs de k et b
        k = (y2 - y1)/(x2 - x1);
        b = (x2*y1 - x1*y2)/(x2 - x1);
        //Calculer la valeur de y
        line=k*x+b;

        //Si la ligne brisee entre en collision avec l'ovale, le joueur �choue.
        if( line < this.hauteur + h  || line   > this.hauteur+Affichage.HEIGHT -  h)
            return true;
        else
            return false;
    }
}
