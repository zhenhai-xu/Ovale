package Control;

import Model.Etat;

import Model.Oiseau;
import Vue.Affichage;


/**Class Avancer
 * Utilisez Runnable pour implémenter des threads,
 * contrôlent principalement le mouvement du parcour,
 * l'ajout et la suppression d'oiseaux,
 */

public class Avancer implements Runnable{
    private Etat etat;
    private Affichage affichage;
    private Oiseau oiseau;

public Avancer(Etat etat, Affichage affichage){
    this.etat=etat;
    this.affichage=affichage;
    this.oiseau=affichage.oiseau;
}

    @Override
    public void run() {
        while (!etat.perdu) {
            try {

                etat.getParcours().setPosition();
                affichage.revalidate();
                affichage.repaint();
                oiseau.ajouteOiseau(affichage);
                oiseau.oiseauMove();
                oiseau.removeOiseau();
                Thread.sleep(1800);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Un message s’affiche avec le score de l’utilisateur quand le joueur est perdu.
    }
}
