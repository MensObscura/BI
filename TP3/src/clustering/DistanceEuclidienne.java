package clustering;

public class DistanceEuclidienne implements Distance {

	@Override
	public double valeur(Donnee d1, Donnee d2) {
		int dimension = d2.nbDimensions();
		double sum=0;
		double diff=0;
		double dist=0;
		
		if(d1.nbDimensions()!=dimension){
			
			return -1;
				
		}

		for(int i =0; i <dimension; i++){
			
			
			diff = d1.valeurDim(i)-d2.valeurDim(i);
			
			Math.pow(diff, 2);
		
			sum+=diff;
			
		}

		dist =Math.sqrt(sum);
		
		return dist ;
	}

}
