package Model;

import Vue.Affichage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**Class Parcours
 * initList() Initialisez la liste et ajoutez des points.
 * ajoutePoint() ajoutez des points.
 * canAjouter() Peut ajouter des points lors de la détection.
 * removeParcours() Supprimer le premier point de la liste.
 */

public class Parcours {
    private ArrayList<Point> parcoursList;
    private int position = 0;

    /**
     * Constructeur
     * Appeler la  initList().
     */
    public Parcours(){
        this.parcoursList = new ArrayList<Point>();
        initList();
    }

    /**
     * Ajouter des points dans la liste au debut,
     */
    public void initList(){
//        int x = Affichage.CentreX+Affichage.WIDTH/2;
        int x =Affichage.CentreX+Affichage.WIDTH/2;
        int y = Affichage.CentreY+Affichage.HEIGHT/2;
        this.parcoursList.add(new Point(x,y));

        do{

            x = 100+ parcoursList.get(parcoursList.size()-1).x;
            y = 200 + new Random().nextInt(Affichage.HAUT-350 ) ;
            this.parcoursList.add(new Point(x,y));
        }while ( x < Affichage.LARG+100);

//    for (Point a : parcoursList)
//        System.out.println(a);
    }

    /**
     * ajouter les points dans liste,
     * @return ParcoursList
     */
    public ArrayList<Point> ajoutePoint(){
        // les coordonnées ont été calculées en retirant la valeur de position à leur valeur d’abscisse.
        this.parcoursList.forEach(a->a.x -= this.position);
        //ajouter le point,suit supprimez les points,inutiles, lorsque de nouveaux points apparaissent à l'écran
        if(canAjouter()){
            int x = 200 + parcoursList.get(parcoursList.size()-1).x;
            int y = 150 + new Random().nextInt(Affichage.HAUT-300 );
            this.parcoursList.add(new Point(x,y));
        }
        return this.parcoursList;
    }

    public ArrayList<Point> getParcoursList(){
        return  this.parcoursList;
    }

    /**setter pour faire avancer la position de quelques pixels*/
    public void setPosition() {
        this.position += 1;
    }

    /**getter la valeur de position*/
    public int getPosition(){
        return position;
    }

    /**
     * si la derniere point dans l'ecran,on peut ajouter des points
     * @return boolean
     */
    public boolean canAjouter() {
        if (parcoursList.get(0).x < 0)
            return true;
        else
            return false;
    }

    /**supprimez le permier point dans le parcoursList  */
    public void removeParcours() {
        if (parcoursList.get(1).x < 0) {
            parcoursList.remove(0);
            //System.out.println("1");
        }
    }
}
