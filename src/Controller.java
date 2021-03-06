import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;

import javax.swing.JFrame;


public class Controller extends JFrame{

	int X;
	int Y;

	int indiceAffichage;

	static View vue;
	static Model myModel;

	public Controller(){
		myModel = new Model();
		myModel.setTitre("Mon Budget");
		myModel.setChamps(new String[]{"Loyer","Alimentation","Cinema","Mutuelle","Jardinage"});
		myModel.setDescription(new String[]{"Loyer minimal",
				"Quotidien, RU","Avengers, Spiderman","Sécurité social","Pelle, rateau"});
		myModel.setDonnees(new Double[]{150.0, 150.0, 75.0, 80.0, 150.0});	

	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public void onClickDescription(){

		if(! vue.getFleches().isEmpty()){
			//Flèche précédente
			if(vue.getFleches().get(0).contains(X,Y)){
				for(Integer i : vue.getPortion().keySet()){
					indiceAffichage = (i+1)%vue.getTableauChamps().length;
					//System.out.println(indiceAffichage);
					//System.err.println(vue.getTableauChamps()[indiceAffichage].getY());
					if(indiceAffichage<0){indiceAffichage=indiceAffichage+vue.getTableauChamps().length;}
					//premier quartier
					if(vue.getTableauChamps()[indiceAffichage].getX() >= vue.getCentreX() && vue.getTableauChamps()[indiceAffichage].getY() < vue.getCentreY()){
						if(vue.getDescription().size()==1){
							Rectangle rectangle = new Rectangle((int)vue.getTableauChamps()[indiceAffichage].getX()+100,
									(int)vue.getTableauChamps()[indiceAffichage].getY(),
									vue.getTableauChamps()[indiceAffichage].width+75,
									vue.getTableauChamps()[indiceAffichage].height+40);
							vue.getDescription().put(indiceAffichage, rectangle);
							vue.getDescription().remove(i);

							Arc2D arc = new Arc2D.Double(vue.getTableauArc2D()[i].getX()-10,
									vue.getTableauArc2D()[indiceAffichage].getY()-10,
									vue.getTableauArc2D()[indiceAffichage].getWidth()+20,
									vue.getTableauArc2D()[indiceAffichage].getHeight()+20,
									vue.getTableauArc2D()[indiceAffichage].getAngleStart(),
									vue.getTableauArc2D()[indiceAffichage].getAngleExtent()+2, Arc2D.PIE);

							vue.getPortion().put(indiceAffichage, arc);
							vue.getPortion().remove(i);

						}
					}//deuxieme quartier
					else if (vue.getTableauChamps()[indiceAffichage].getX() < vue.getCentreX() && vue.getTableauChamps()[indiceAffichage].getY() <= vue.getCentreY()){
						if(vue.getDescription().size() == 1){
							Rectangle rectangle = new Rectangle((int)vue.getTableauChamps()[indiceAffichage].getX()-175,
									(int)vue.getTableauChamps()[indiceAffichage].getY(),
									vue.getTableauChamps()[indiceAffichage].width+75,
									vue.getTableauChamps()[indiceAffichage].height+40);
							vue.getDescription().put(indiceAffichage, rectangle);
							vue.getDescription().remove(i);

							Arc2D arc = new Arc2D.Double(vue.getTableauArc2D()[i].getX()-10,
									vue.getTableauArc2D()[indiceAffichage].getY()-10,
									vue.getTableauArc2D()[indiceAffichage].getWidth()+20,
									vue.getTableauArc2D()[indiceAffichage].getHeight()+20,
									vue.getTableauArc2D()[indiceAffichage].getAngleStart(),
									vue.getTableauArc2D()[indiceAffichage].getAngleExtent()+2, Arc2D.PIE);

							vue.getPortion().put(indiceAffichage, arc);
							vue.getPortion().remove(i);
						}
					}//troisieme quartier
					else if (vue.getTableauChamps()[indiceAffichage].getX() < vue.getCentreX() && vue.getTableauChamps()[indiceAffichage].getY() >= vue.getCentreY()){
						if(vue.getDescription().size() == 1){
							Rectangle rectangle = new Rectangle((int)vue.getTableauChamps()[indiceAffichage].getX()-175,
									(int)vue.getTableauChamps()[indiceAffichage].getY()-40,
									vue.getTableauChamps()[indiceAffichage].width+75,
									vue.getTableauChamps()[indiceAffichage].height+40);
							vue.getDescription().put(indiceAffichage, rectangle);
							vue.getDescription().remove(i);

							Arc2D arc = new Arc2D.Double(vue.getTableauArc2D()[indiceAffichage].getX()-10,
									vue.getTableauArc2D()[indiceAffichage].getY()-10,
									vue.getTableauArc2D()[indiceAffichage].getWidth()+20,
									vue.getTableauArc2D()[indiceAffichage].getHeight()+20,
									vue.getTableauArc2D()[indiceAffichage].getAngleStart(),
									vue.getTableauArc2D()[indiceAffichage].getAngleExtent()+2, Arc2D.PIE);

							vue.getPortion().put(indiceAffichage, arc);
							vue.getPortion().remove(i);

						}
					}//quatrieme quartier
					else if (vue.getTableauChamps()[indiceAffichage].getX() > vue.getCentreX() && vue.getTableauChamps()[indiceAffichage].getY() >= vue.getCentreY()){

						if(vue.getDescription().size() == 1){
							Rectangle rectangle = new Rectangle((int)vue.getTableauChamps()[indiceAffichage].getX()+100,
									(int)vue.getTableauChamps()[indiceAffichage].getY()-40,
									vue.getTableauChamps()[indiceAffichage].width+75,
									vue.getTableauChamps()[indiceAffichage].height+40);
							vue.getDescription().put(indiceAffichage, rectangle);
							vue.getDescription().remove(i);

							Arc2D arc = new Arc2D.Double(vue.getTableauArc2D()[indiceAffichage].getX()-10,
									vue.getTableauArc2D()[indiceAffichage].getY()-10,
									vue.getTableauArc2D()[indiceAffichage].getWidth()+20,
									vue.getTableauArc2D()[indiceAffichage].getHeight()+20,
									vue.getTableauArc2D()[indiceAffichage].getAngleStart(),
									vue.getTableauArc2D()[indiceAffichage].getAngleExtent()+2, Arc2D.PIE);

							vue.getPortion().put(indiceAffichage, arc);
							vue.getPortion().remove(i);

						}
					}
				}					
			}//Flèche suivante			
			else if(vue.getFleches().get(1).contains(X,Y)){
				for(Integer i : vue.getPortion().keySet()){
					indiceAffichage = (i-1)%vue.getTableauChamps().length;
					//System.out.println(indiceAffichage);
					//System.err.println(vue.getTableauChamps()[indiceAffichage].getY());
					if(indiceAffichage<0){indiceAffichage=indiceAffichage+vue.getTableauChamps().length;}
					//premier quartier
					if(vue.getTableauChamps()[indiceAffichage].getX() >= vue.getCentreX() && vue.getTableauChamps()[indiceAffichage].getY() < vue.getCentreY()){
						if(vue.getDescription().size()==1){
							Rectangle rectangle = new Rectangle((int)vue.getTableauChamps()[indiceAffichage].getX()+100,
									(int)vue.getTableauChamps()[indiceAffichage].getY(),
									vue.getTableauChamps()[indiceAffichage].width+75,
									vue.getTableauChamps()[indiceAffichage].height+40);
							vue.getDescription().put(indiceAffichage, rectangle);
							vue.getDescription().remove(i);

							Arc2D arc = new Arc2D.Double(vue.getTableauArc2D()[i].getX()-10,
									vue.getTableauArc2D()[indiceAffichage].getY()-10,
									vue.getTableauArc2D()[indiceAffichage].getWidth()+20,
									vue.getTableauArc2D()[indiceAffichage].getHeight()+20,
									vue.getTableauArc2D()[indiceAffichage].getAngleStart(),
									vue.getTableauArc2D()[indiceAffichage].getAngleExtent()+2, Arc2D.PIE);

							vue.getPortion().put(indiceAffichage, arc);
							vue.getPortion().remove(i);

						}
					}//deuxieme quartier
					else if (vue.getTableauChamps()[indiceAffichage].getX() < vue.getCentreX() && vue.getTableauChamps()[indiceAffichage].getY() <= vue.getCentreY()){
						if(vue.getDescription().size() == 1){
							Rectangle rectangle = new Rectangle((int)vue.getTableauChamps()[indiceAffichage].getX()-175,
									(int)vue.getTableauChamps()[indiceAffichage].getY(),
									vue.getTableauChamps()[indiceAffichage].width+75,
									vue.getTableauChamps()[indiceAffichage].height+40);
							vue.getDescription().put(indiceAffichage, rectangle);
							vue.getDescription().remove(i);

							Arc2D arc = new Arc2D.Double(vue.getTableauArc2D()[i].getX()-10,
									vue.getTableauArc2D()[indiceAffichage].getY()-10,
									vue.getTableauArc2D()[indiceAffichage].getWidth()+20,
									vue.getTableauArc2D()[indiceAffichage].getHeight()+20,
									vue.getTableauArc2D()[indiceAffichage].getAngleStart(),
									vue.getTableauArc2D()[indiceAffichage].getAngleExtent()+2, Arc2D.PIE);

							vue.getPortion().put(indiceAffichage, arc);
							vue.getPortion().remove(i);
						}
					}//troisieme quartier
					else if (vue.getTableauChamps()[indiceAffichage].getX() < vue.getCentreX() && vue.getTableauChamps()[indiceAffichage].getY() >= vue.getCentreY()){
						if(vue.getDescription().size() == 1){
							Rectangle rectangle = new Rectangle((int)vue.getTableauChamps()[indiceAffichage].getX()-175,
									(int)vue.getTableauChamps()[indiceAffichage].getY()-40,
									vue.getTableauChamps()[indiceAffichage].width+75,
									vue.getTableauChamps()[indiceAffichage].height+40);
							vue.getDescription().put(indiceAffichage, rectangle);
							vue.getDescription().remove(i);

							Arc2D arc = new Arc2D.Double(vue.getTableauArc2D()[indiceAffichage].getX()-10,
									vue.getTableauArc2D()[indiceAffichage].getY()-10,
									vue.getTableauArc2D()[indiceAffichage].getWidth()+20,
									vue.getTableauArc2D()[indiceAffichage].getHeight()+20,
									vue.getTableauArc2D()[indiceAffichage].getAngleStart(),
									vue.getTableauArc2D()[indiceAffichage].getAngleExtent()+2, Arc2D.PIE);

							vue.getPortion().put(indiceAffichage, arc);
							vue.getPortion().remove(i);

						}
					}//quatrieme quartier
					else if (vue.getTableauChamps()[indiceAffichage].getX() > vue.getCentreX() && vue.getTableauChamps()[indiceAffichage].getY() >= vue.getCentreY()){

						if(vue.getDescription().size() == 1){
							Rectangle rectangle = new Rectangle((int)vue.getTableauChamps()[indiceAffichage].getX()+100,
									(int)vue.getTableauChamps()[indiceAffichage].getY()-40,
									vue.getTableauChamps()[indiceAffichage].width+75,
									vue.getTableauChamps()[indiceAffichage].height+40);
							vue.getDescription().put(indiceAffichage, rectangle);
							vue.getDescription().remove(i);

							Arc2D arc = new Arc2D.Double(vue.getTableauArc2D()[indiceAffichage].getX()-10,
									vue.getTableauArc2D()[indiceAffichage].getY()-10,
									vue.getTableauArc2D()[indiceAffichage].getWidth()+20,
									vue.getTableauArc2D()[indiceAffichage].getHeight()+20,
									vue.getTableauArc2D()[indiceAffichage].getAngleStart(),
									vue.getTableauArc2D()[indiceAffichage].getAngleExtent()+2, Arc2D.PIE);

							vue.getPortion().put(indiceAffichage, arc);
							vue.getPortion().remove(i);

						}
					}
				}					
			
				
			}
		}

		for (int i = 0; i < vue.getTableauChamps().length; i++) {
			//Si on clique sur un rectangle ou sur un arc2D
			if(vue.getTableauChamps()[i].contains(X,Y) || vue.getTableauArc2D()[i].contains(X,Y)){

				Polygon triangle1 = new Polygon();
				Polygon triangle2 = new Polygon();

				triangle1.addPoint(950, 10);
				triangle1.addPoint(930, 40);			
				triangle1.addPoint(970, 40);


				triangle2.addPoint(950, 80);
				triangle2.addPoint(930, 50);			
				triangle2.addPoint(970, 50);

				vue.getFleches().add(triangle1);
				vue.getFleches().add(triangle2);

				//premier quartier
				if(vue.getTableauChamps()[i].getX() >= vue.getCentreX() && vue.getTableauChamps()[i].getY() < vue.getCentreY()){

					if(vue.getDescription().isEmpty()){
						Rectangle rectangle = new Rectangle((int)vue.getTableauChamps()[i].getX()+100,
								(int)vue.getTableauChamps()[i].getY(),
								vue.getTableauChamps()[i].width+75,
								vue.getTableauChamps()[i].height+40);
						vue.getDescription().put(i, rectangle);
						Arc2D arc = new Arc2D.Double(vue.getTableauArc2D()[i].getX()-10,
								vue.getTableauArc2D()[i].getY()-10,
								vue.getTableauArc2D()[i].getWidth()+20,
								vue.getTableauArc2D()[i].getHeight()+20,
								vue.getTableauArc2D()[i].getAngleStart(),
								vue.getTableauArc2D()[i].getAngleExtent()+2, Arc2D.PIE);

						vue.getPortion().put(i, arc);
					}else{
						vue.getDescription().clear();
						vue.getPortion().clear();
						vue.getFleches().clear();

					}

				}
				//deuxieme quartier
				else if (vue.getTableauChamps()[i].getX() < vue.getCentreX() && vue.getTableauChamps()[i].getY() <= vue.getCentreY()){
					if(vue.getDescription().isEmpty()){
						Rectangle rectangle = new Rectangle((int)vue.getTableauChamps()[i].getX()-175,
								(int)vue.getTableauChamps()[i].getY(),
								vue.getTableauChamps()[i].width+75,
								vue.getTableauChamps()[i].height+40);
						vue.getDescription().put(i, rectangle);
						Arc2D arc = new Arc2D.Double(vue.getTableauArc2D()[i].getX()-10,
								vue.getTableauArc2D()[i].getY()-10,
								vue.getTableauArc2D()[i].getWidth()+20,
								vue.getTableauArc2D()[i].getHeight()+20,
								vue.getTableauArc2D()[i].getAngleStart(),
								vue.getTableauArc2D()[i].getAngleExtent()+2, Arc2D.PIE);

						vue.getPortion().put(i, arc);
					}
					else{
						vue.getDescription().clear();
						vue.getPortion().clear();
						vue.getFleches().clear();
					}
				}
				//troisieme quartier
				else if (vue.getTableauChamps()[i].getX() < vue.getCentreX() && vue.getTableauChamps()[i].getY() >= vue.getCentreY()){
					if(vue.getDescription().isEmpty()){
						Rectangle rectangle = new Rectangle((int)vue.getTableauChamps()[i].getX()-175,
								(int)vue.getTableauChamps()[i].getY()-40,
								vue.getTableauChamps()[i].width+75,
								vue.getTableauChamps()[i].height+40);
						vue.getDescription().put(i, rectangle);
						Arc2D arc = new Arc2D.Double(vue.getTableauArc2D()[i].getX()-10,
								vue.getTableauArc2D()[i].getY()-10,
								vue.getTableauArc2D()[i].getWidth()+20,
								vue.getTableauArc2D()[i].getHeight()+20,
								vue.getTableauArc2D()[i].getAngleStart(),
								vue.getTableauArc2D()[i].getAngleExtent()+2, Arc2D.PIE);

						vue.getPortion().put(i, arc);
					}
					else{
						vue.getDescription().clear();
						vue.getPortion().clear();
						vue.getFleches().clear();
					}
				}
				//quatrieme quartier
				else if (vue.getTableauChamps()[i].getX() > vue.getCentreX() && vue.getTableauChamps()[i].getY() >= vue.getCentreY()){
					if(vue.getDescription().isEmpty()){
						Rectangle rectangle = new Rectangle((int)vue.getTableauChamps()[i].getX()+100,
								(int)vue.getTableauChamps()[i].getY()-40,
								vue.getTableauChamps()[i].width+75,
								vue.getTableauChamps()[i].height+40);
						vue.getDescription().put(i, rectangle);
						Arc2D arc = new Arc2D.Double(vue.getTableauArc2D()[i].getX()-10,
								vue.getTableauArc2D()[i].getY()-10,
								vue.getTableauArc2D()[i].getWidth()+20,
								vue.getTableauArc2D()[i].getHeight()+20,
								vue.getTableauArc2D()[i].getAngleStart(),
								vue.getTableauArc2D()[i].getAngleExtent()+2, Arc2D.PIE);

						vue.getPortion().put(i, arc);
					}
					else{
						vue.getDescription().clear();
						vue.getPortion().clear();
						vue.getFleches().clear();
					}
				}
			}

			vue.repaint();

		}
	}

	public static void main(String[] args) {
		Controller m = new Controller();
		vue = new View(myModel);

		m.setTitle("Camembert");
		m.setSize(1000,600);
		m.setLocationRelativeTo(null);               
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.add(vue);

		m.setVisible(true);
	}
}
