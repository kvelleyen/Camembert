
public class Model implements ModelObserver{
	
	public String titre;
	public String[] champs;
	public String[] description;
	public Double[] donnees;
	
	
	public Double[] getDonnees() {
		return donnees;
	}
	public void setDonnees(Double[] donnees) {
		this.donnees = donnees;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String[] getChamps() {
		return champs;
	}
	public void setChamps(String[] champs) {
		this.champs = champs;
	}
	public String[] getDescription() {
		return description;
	}
	public void setDescription(String[] description) {
		this.description = description;
	}
	@Override
	public void modelNotify() {
		// TODO Auto-generated method stub
	
	}
	
}
