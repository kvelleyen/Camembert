import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javafx.geometry.Rectangle2D;

import javax.swing.JComponent;



public class View extends JComponent implements MouseListener{

	Controller controller;

	//Mon modèle de données
	Model myModel;

	//count pour la conversion des données en camembert
	Double count;

	//les nouvelles données après conversion
	Double[] newDonnees;

	//tableau de couleur
	Color[] couleurs;

	//indice pour dessiner chaque portion du camembert
	Double indice;

	//propriétés du camembert
	int CentreX;
	int CentreY;
	int rayon;

	//Tableau de Titre
	Rectangle[] TableauChamps;

	//Arraylist description
	HashMap<Integer, Rectangle> description;
	//ArrayList<Rectangle> description;

	public View(Model myModel) {
		this.myModel = myModel;

		this.addMouseListener(this);


		controller = new Controller();
		count = 0.0;
		couleurs = new Color[myModel.getDonnees().length];
		newDonnees = new Double[myModel.getDonnees().length];
		CentreX = 500;
		CentreY = 300;
		rayon = 200;

		TableauChamps = new Rectangle[myModel.getDonnees().length];

		//description = new ArrayList<Rectangle>();
		description = new HashMap<Integer,Rectangle>();

		for (int i = 0; i < myModel.getDonnees().length; i++){
			count = count + myModel.getDonnees()[i];
		}
		for (int i = 0; i < myModel.getDonnees().length; i++){
			newDonnees[i] = (myModel.getDonnees()[i] * 360) / count;

			Random rand = new Random();
			int r = Math.abs(rand.nextInt() % 255);
			int g = Math.abs(rand.nextInt() % 255);
			int b = Math.abs(rand.nextInt() % 255);

			Color randomColor = new Color(r, g, b);
			couleurs[i] = randomColor;
		}

	}

	//Getters and setters
	public int getCentreX() {
		return CentreX;
	}
	public void setCentreX(int centreX) {
		CentreX = centreX;
	}
	public int getCentreY() {
		return CentreY;
	}
	public void setCentreY(int centreY) {
		CentreY = centreY;
	}
	public Rectangle[] getTableauChamps() {
		return TableauChamps;
	}
	public void setTableauChamps(Rectangle[] tableauChamps) {
		TableauChamps = tableauChamps;
	}
	public HashMap<Integer, Rectangle> getDescription() {
		return description;
	}
	public void setDescription(HashMap<Integer, Rectangle> description) {
		this.description = description;
	}
	@Override
	public void mouseClicked(MouseEvent e) {

		controller.setX(e.getX());
		controller.setY(e.getY());

		controller.onClickDescription();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void paintComponent(Graphics g){
		//super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		//remise à zéro à chaque fois que la méthode repaint sera appelé
		indice = 0.0;

		g2.setFont(new Font("Comic Sans MS", Font.BOLD, 15));


		//background
		g2.setColor(Color.white);
		g2.fillRect(0, 0, 1000, 600);

		//antialiasing
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);



		for (int i = 0; i < myModel.getDonnees().length; i++){

			//portion du camembert
			g2.setColor(couleurs[i]);
			Arc2D monArc = new Arc2D.Double(300, 100, 400, 400, indice, newDonnees[i], Arc2D.PIE);
			g2.fill(monArc);

			//etiquette au centre de l'arc
			Double arcCenter = (newDonnees[i]/2) + indice;

			/*System.out.println("indice " + indice);
			System.out.println("nouvelles données " + newDonnees[i]);
			 */

			int XLine = CentreX + (int)(rayon * Math.cos(Math.toRadians(arcCenter)));
			int YLine = CentreY - (int)(rayon * Math.sin(Math.toRadians(arcCenter)));

			if(arcCenter >= 0 && arcCenter < 90){
				g2.drawLine(XLine,YLine,XLine + 20,YLine - 20);

				Rectangle rect = new Rectangle(XLine + 20, YLine - 60,
						100, 40);
				TableauChamps[i]=rect;

				g2.fillRect(rect.x, rect.y, rect.width, rect.height);
				g2.setColor(Color.white);

				//etiquette
				FontMetrics metricsChamp = g2.getFontMetrics();
				int advChamp = metricsChamp.stringWidth(myModel.getChamps()[i]);
				//System.err.println("advChamp --> " + advChamp);

				//if(advChamp >= 77){}

				g2.drawString(myModel.getChamps()[i], (XLine + 70) - advChamp/2
						, YLine - 40);
				g2.setColor(Color.white);
				g2.drawString(myModel.getChamps()[i], (XLine + 70) - advChamp/2
						, YLine - 40);

			}
			else if(arcCenter >= 90 && arcCenter < 180){
				g2.drawLine(XLine,YLine,XLine - 20,YLine - 20);

				Rectangle rect = new Rectangle(XLine - 120, YLine - 60,
						100, 40);

				TableauChamps[i]=rect;

				g2.fillRect((int) rect.getX(),(int) rect.getY(),rect.width, rect.height);

				g2.setColor(Color.white);

				//etiquette
				FontMetrics metricsChamp = g2.getFontMetrics();
				int advChamp = metricsChamp.stringWidth(myModel.getChamps()[i]);

				g2.drawString(myModel.getChamps()[i], (XLine - 70) - advChamp/2
						, YLine - 40);
			}
			else if(arcCenter >= 180 && arcCenter < 270){
				g2.drawLine(XLine,YLine,XLine - 20,YLine + 20);

				Rectangle rect = new Rectangle(XLine - 120, YLine + 20,
						100, 40);

				TableauChamps[i]=rect;

				g2.fillRect((int) rect.getX(),(int) rect.getY(),rect.width, rect.height);

				g2.setColor(Color.white);

				//etiquette
				FontMetrics metricsChamp = g2.getFontMetrics();
				int advChamp = metricsChamp.stringWidth(myModel.getChamps()[i]);

				g2.drawString(myModel.getChamps()[i], (XLine - 70) - advChamp/2
						, YLine + 40);
			}
			else if(arcCenter >= 270 && arcCenter < 360){
				g2.drawLine(XLine,YLine,XLine + 20,YLine + 20);

				Rectangle rect = new Rectangle(XLine + 20, YLine + 20,
						100, 40);

				TableauChamps[i]=rect;

				g2.fillRect((int) rect.getX(),(int) rect.getY(),rect.width, rect.height);
				g2.setColor(Color.white);

				//etiquette
				FontMetrics metricsChamp = g2.getFontMetrics();
				int advChamp = metricsChamp.stringWidth(myModel.getChamps()[i]);

				g2.drawString(myModel.getChamps()[i], (XLine + 70) - advChamp/2
						, YLine + 40);
			}


			g2.setColor(Color.white);
			Arc2D interArc = new Arc2D.Double(300, 100, 400, 400, indice, 0.5
					, Arc2D.PIE);
			g2.fill(interArc);

			indice = indice + newDonnees[i];
		}

		//interieur du camembert
		g2.setColor(Color.gray);
		g2.fillOval(350, 150, 300, 300);

		//cercle interieur
		g2.setColor(Color.white);
		g2.drawOval(350, 150, 300, 300);

		//centrer le titre
		FontMetrics metricsTitre = g2.getFontMetrics();
		int advTitre = metricsTitre.stringWidth(myModel.getTitre());
		g2.setColor(Color.white);
		g2.drawString(myModel.getTitre(), 500-(advTitre/2), 300);


		for(Integer i : description.keySet()){
			if(description.get(i).getX() >= CentreX && description.get(i).getY() < CentreY){
				g2.setColor(Color.lightGray);
				//rectangle descriptif
				g2.fillRect((int)description.get(i).getX(),(int) description.get(i).getY(), description.get(i).width, description.get(i).height);

				//description

				System.err.println(myModel.getDescription()[i].length());

				g2.setColor(Color.black);
				g2.drawString(myModel.getDescription()[i], (int)description.get(i).getX() + 10,(int) description.get(i).getY() + 15);



				int index = 15;
				int limite = 20;

				for (int j = 0; j < myModel.getDescription()[i].length(); j=j+15) {
					System.out.println(myModel.getDescription()[i].substring(j,myModel.getDescription()[i].length()));
				}



				/*g2.drawString(s,(int) description.get(i).getX() + 10,(int) description.get(i).getY() + index2);
						index2 = index2 + 15;*/




				//g2.drawString(myModel.getDescription()[i].substring(0,20), (int)description.get(i).getX() + 10,(int) description.get(i).getY() + 15);
			}

			else if (description.get(i).getX() < CentreX && description.get(i).getY() < CentreY) {
				g2.setColor(Color.lightGray);
				//rectangle descriptif
				g2.fillRect((int)description.get(i).getX(),(int) description.get(i).getY(), description.get(i).width, description.get(i).height);					
				//description
				g2.setColor(Color.black);
				g2.drawString(myModel.getDescription()[i], (int)description.get(i).getX(),(int) description.get(i).getY());
			}
			else if (description.get(i).getX() < CentreX && description.get(i).getY() >= CentreY) {
				g2.setColor(Color.lightGray);
				//rectangle descriptif
				g2.fillRect((int)description.get(i).getX(),(int) description.get(i).getY(), description.get(i).width, description.get(i).height);					
				//description
				g2.setColor(Color.black);
				g2.drawString(myModel.getDescription()[i], (int)description.get(i).getX(),(int) description.get(i).getY());
			}
			else if (description.get(i).getX() > CentreX && description.get(i).getY() >= CentreY) {
				g2.setColor(Color.lightGray);
				//rectangle descriptif
				g2.fillRect((int)description.get(i).getX(),(int) description.get(i).getY(), description.get(i).width, description.get(i).height);					
				//description
				g2.setColor(Color.black);
				g2.drawString(myModel.getDescription()[i], (int)description.get(i).getX(),(int) description.get(i).getY());
			}

		}

	}

}
