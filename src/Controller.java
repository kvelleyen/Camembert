import java.awt.Rectangle;

import javax.swing.JFrame;


public class Controller extends JFrame{

	int X;
	int Y;

	static View vue;
	static Model myModel;

	public Controller(){
		myModel = new Model();
		myModel.setTitre("Mon Budget");
		myModel.setChamps(new String[]{"Loyer","Alimentation","Cinema","Mutuelle"});
		myModel.setDescription(new String[]{"Loyer minimal hors charges",
				"Quotidien, RU, McDo","Avengers, Spiderman, Iron Man","Sécurité social étudiante"});
		myModel.setDonnees(new Double[]{150.0, 150.0, 75.0, 80.0});	
		
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
		for (int i = 0; i < vue.getTableauChamps().length; i++) {
			//Si on clique sur un rectangle
			if(vue.getTableauChamps()[i].contains(X,Y)){
				//premier quartier
				if(X >= vue.getCentreX() && Y < vue.getCentreY()){
					if(vue.getDescription().get(i) == null){
						Rectangle rectangle = new Rectangle((int)vue.getTableauChamps()[i].getX()+100,
								(int)vue.getTableauChamps()[i].getY(),
								vue.getTableauChamps()[i].width+75,
								vue.getTableauChamps()[i].height+40);
						vue.getDescription().put(i, rectangle);
					}else{
						vue.getDescription().remove(i);
					}
				}
					//deuxieme quartier
					else if (X < vue.getCentreX() && Y <= vue.getCentreY()){
						if(vue.getDescription().get(i) == null){
							Rectangle rectangle = new Rectangle((int)vue.getTableauChamps()[i].getX()-175,
									(int)vue.getTableauChamps()[i].getY(),
									vue.getTableauChamps()[i].width+75,
									vue.getTableauChamps()[i].height+40);
							vue.getDescription().put(i, rectangle);
						}
						else{
							vue.getDescription().remove(i);
						}
					}
					//troisieme quartier
					else if (X < vue.getCentreX() && Y >= vue.getCentreY()){
						if(vue.getDescription().get(i) == null){
							Rectangle rectangle = new Rectangle((int)vue.getTableauChamps()[i].getX()-175,
									(int)vue.getTableauChamps()[i].getY()-40,
									vue.getTableauChamps()[i].width+75,
									vue.getTableauChamps()[i].height+40);
							vue.getDescription().put(i, rectangle);
						}
						else{
							vue.getDescription().remove(i);
						}
					}
					//quatrieme quartier
					else if (X > vue.getCentreX() && Y >= vue.getCentreY()){
						if(vue.getDescription().get(i) == null){
							Rectangle rectangle = new Rectangle((int)vue.getTableauChamps()[i].getX()+100,
									(int)vue.getTableauChamps()[i].getY()-40,
									vue.getTableauChamps()[i].width+75,
									vue.getTableauChamps()[i].height+40);
							vue.getDescription().put(i, rectangle);
						}
						else{
							vue.getDescription().remove(i);
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
