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
import javafx.scene.shape.Arc;

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
	int centreX;
	int centreY;
	int rayon;

	//Tableau de Titre
	Rectangle[] tableauChamps;

	//Arraylist description
	HashMap<Integer, Rectangle> description;
	
	HashMap<Integer, Arc2D> portion;
	
	//Tableau Arc2D
	Arc2D[] tableauArc2D;
	
	
	public View(Model myModel) {
		this.myModel = myModel;

		this.addMouseListener(this);


		controller = new Controller();
		count = 0.0;
		couleurs = new Color[myModel.getDonnees().length];
		newDonnees = new Double[myModel.getDonnees().length];
		centreX = 500;
		centreY = 300;
		rayon = 200;

		tableauChamps = new Rectangle[myModel.getDonnees().length];

		//description = new ArrayList<Rectangle>();
		description = new HashMap<Integer,Rectangle>();

		tableauArc2D = new Arc2D[myModel.getDonnees().length];
		
		portion = new HashMap<Integer,Arc2D>();
		
		
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
		return centreX;
	}
	public void setCentreX(int centreX) {
		this.centreX = centreX;
	}
	public int getCentreY() {
		return centreY;
	}
	public void setCentreY(int centreY) {
		this.centreY = centreY;
	}
	public Rectangle[] getTableauChamps() {
		return tableauChamps;
	}
	public void setTableauChamps(Rectangle[] tableauChamps) {
		this.tableauChamps = tableauChamps;
	}
	public HashMap<Integer, Rectangle> getDescription() {
		return description;
	}
	public void setDescription(HashMap<Integer, Rectangle> description) {
		this.description = description;
	}
	
	public Arc2D[] getTableauArc2D() {
		return tableauArc2D;
	}

	public void setTableauArc2D(Arc2D[] tableauArc2D) {
		this.tableauArc2D = tableauArc2D;
	}

	public HashMap<Integer, Arc2D> getPortion() {
		return portion;
	}

	public void setPortion(HashMap<Integer, Arc2D> portion) {
		this.portion = portion;
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
			
			
			
			//if (tableauArc2D[tableauArc2D.length-1]==null) {
				Arc2D monArc = new Arc2D.Double(300, 100, 400, 400, indice, newDonnees[i], Arc2D.PIE);
				tableauArc2D[i] = monArc;
				g2.fill(monArc);
				//System.out.println("View.paintComponent()");
			//}else{
				g2.fill(tableauArc2D[i]);
			//}

			//etiquette au centre de l'arc
			Double arcCenter = (newDonnees[i]/2) + indice;

			/*System.out.println("indice " + indice);
			System.out.println("nouvelles données " + newDonnees[i]);
			 */

			int XLine = centreX + (int)(rayon * Math.cos(Math.toRadians(arcCenter)));
			int YLine = centreY - (int)(rayon * Math.sin(Math.toRadians(arcCenter)));

			if(arcCenter >= 0 && arcCenter < 90){
				g2.drawLine(XLine,YLine,XLine + 20,YLine - 20);

				Rectangle rect = new Rectangle(XLine + 20, YLine - 60,
						100, 40);
				tableauChamps[i]=rect;

				g2.fillRect(rect.x, rect.y, rect.width, rect.height);
				g2.setColor(Color.white);

				//etiquette
				FontMetrics metricsChamp = g2.getFontMetrics();
				int advChamp = metricsChamp.stringWidth(myModel.getChamps()[i]);
				

				g2.drawString(myModel.getChamps()[i], (XLine + 70) - advChamp/2
						, YLine - 40);
				g2.setColor(Color.white);
				

			}
			else if(arcCenter >= 90 && arcCenter < 180){
				g2.drawLine(XLine,YLine,XLine - 20,YLine - 20);

				Rectangle rect = new Rectangle(XLine - 120, YLine - 60,
						100, 40);

				tableauChamps[i]=rect;

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

				tableauChamps[i]=rect;

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

				tableauChamps[i]=rect;

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
		
		for(Integer i : portion.keySet()){
			
			g2.setColor(couleurs[i]);
			g2.fill(portion.get(i));			
		}
		

		//interieur du camembert
		g2.setColor(Color.gray);
		g2.fillOval(350, 150, 300, 300);

		//cercle interieur
		g2.setColor(Color.white);
		g2.drawOval(350, 150, 300, 300);

		//centrer le titre
		FontMetrics metrics = g2.getFontMetrics();
		int advTitre = metrics.stringWidth(myModel.getTitre());
		int advTotal = metrics.stringWidth(String.valueOf(count));
		
		g2.setColor(Color.white);
		g2.drawString(myModel.getTitre(), 500-(advTitre/2), 300);
		
		g2.drawString(String.valueOf(count),
				500-(advTotal/2), 320);


		for(Integer i : description.keySet()){
			if(description.get(i).getX() >= centreX && description.get(i).getY() < centreY){
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

			else if (description.get(i).getX() < centreX && description.get(i).getY() < centreY) {
				g2.setColor(Color.lightGray);
				//rectangle descriptif
				g2.fillRect((int)description.get(i).getX(),(int) description.get(i).getY(), description.get(i).width, description.get(i).height);					
				//description
				g2.setColor(Color.black);
				g2.drawString(myModel.getDescription()[i], (int)description.get(i).getX(),(int) description.get(i).getY());
			}
			else if (description.get(i).getX() < centreX && description.get(i).getY() >= centreY) {
				g2.setColor(Color.lightGray);
				//rectangle descriptif
				g2.fillRect((int)description.get(i).getX(),(int) description.get(i).getY(), description.get(i).width, description.get(i).height);					
				//description
				g2.setColor(Color.black);
				g2.drawString(myModel.getDescription()[i], (int)description.get(i).getX(),(int) description.get(i).getY());
			}
			else if (description.get(i).getX() > centreX && description.get(i).getY() >= centreY) {
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
