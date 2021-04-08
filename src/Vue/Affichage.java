package Vue;

import Model.Oiseau;
import Model.Etat;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.io.IOException;
import java.util.ArrayList;

/**Class Affichage Contenu de la fenêtre de peinture.
 *
 *drawLine() Utilisez QuadCurve2D pour dessiner une courbe de Bézier.
 *drawOval() Dessinez une ellipse.
 *drawOiseau()Dessine l'oiseau.
 *paint() Appelez la fonction ci-dessus pour dessiner du contenu.
 */

public class Affichage extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//La largeur de la fenêtre.
    //la hauteur de la fenêtre.
    public static final int LARG = 600;
    public static final int HAUT = 400;

     //La largeur de l'objet.
     //La hauteur de l'objet.
    public static final int WIDTH = 60;
    public static final int HEIGHT = 100;

    //Axe X de l'objet.
     //Axe y de l'objet.
    public static final int CentreX = 20;
    public static final int CentreY = 200;

    //Épaisseur de ligne
    public static final float Stroke = 4f;

    private Etat etat ;
    public Oiseau oiseau = new Oiseau(this);
    /**
     * Constructeur
     * @param etat
     */
    public Affichage(Etat etat) throws IOException {
        //Définir la taille de la panel
        setPreferredSize(new Dimension(LARG, HAUT));
        //Obtenez etat
        this.etat=etat;
        repaint();

    }

    /**
     * Le premier point de la liste n'est pas utilisé.
     * La courbe dessinée au début du jeu manquera un segment.
     * jouter un point pour compléter le point manquant.
     * */
    public void drawLine(Graphics2D g){
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(Stroke));
        ArrayList<Point> L1 = this.etat.getParcours().ajoutePoint();

        //Le premier point de la courbe de Bézier n'étant pas dessiné, nous ajoutons manuellement un point, sinon
        //Lors du dessin de la courbe, il y aura une section manquante.
        //Si ligne 73-80 est commenté, une courbe manquera au début de la partie.
        QuadCurve2D courbe = new QuadCurve2D.Double();
        double debut0_x = L1.get(0).getX() ;
        double debut0_y = L1.get(0).getY() ;
        double fin0_x = (L1.get(0).getX() + L1.get(1).getX()) /2;
        double fin0_y = (L1.get(0).getY() + L1.get(1).getY()) /2;
        courbe.setCurve(new Point2D.Double(debut0_x, debut0_y),
                new Point2D.Double(L1.get(0).getX(), L1.get(0).getY()),
                new Point2D.Double(fin0_x,fin0_y));
        g.draw(courbe);

        //
        //Courbe suivante.
        for (int i = 0; i < L1.size() - 2; i++) {
            double p0x = (L1.get(i).getX() + L1.get(i + 1).getX()) / 2;
            double p0y = (L1.get(i).getY() + L1.get(i + 1).getY()) / 2;
            double p1x = (L1.get(i + 1).getX() + L1.get(i + 2).getX()) / 2;
            double p1y = (L1.get(i + 1).getY() + L1.get(i + 2).getY()) / 2;
            Point2D.Double start = new Point2D.Double(p0x , p0y);
            Point2D.Double ctrl = new Point2D.Double(L1.get(i + 1).getX(), L1.get(i + 1).getY());
            Point2D.Double fin = new Point2D.Double(p1x , p1y);
            courbe.setCurve(start, ctrl, fin);
            g.draw(courbe);
        }
    }

    /**Dessinez une ellipse*/
    public void drawOval(Graphics2D g){
        //g.drawOval(Oval_x, etat.getHauteur(), LARG_Oval, HAUT_Oval);
        g.setColor(Color.BLUE);
        g.setStroke(new BasicStroke(Stroke));
        g.drawOval(CentreX, etat.getHauteur(), WIDTH, HEIGHT);
    }

    /**getEtat,
     *  utilisé dans d'autre class*/
    public Etat getEtat() {
        return etat;
    }

    /**Dessinez les Oiseau dans la liste*/
    public void drawOiseau(Graphics2D g){
        for(int i=0;i<oiseau.getOlist().size();i++) {
            Oiseau oiseau= this.oiseau.getOlist().get(i);
            g.drawImage(oiseau.getImg(), oiseau.getPosition(), oiseau.getHauteur(),oiseau.getW(),oiseau.getH(),null);
        }
    }

    /**Appelez la fonction
     * drawOval(),drawLine(g2),drawOiseau(g2),
     * Dessinez du texte pour afficher le score,
     */
    @Override
    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.clearRect(0, 0, LARG, HAUT);

        // Dessine un ovale
        drawOval(g2);

        //Dessine la ligne brisé
        drawLine(g2);

        //Dessine l'oiseau
        drawOiseau(g2);

        //Dessine la valeur de la position
        g2.drawString("Position: "+ etat.getParcours().getPosition(),20, 50);
    }

}