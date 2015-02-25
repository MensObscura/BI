package exemples ;
import clustering.* ;

/**
 * c'est l'exercice 2 du TD. 
 * Comme tout est fixé comme dans l'exercice (les points, les centres), ça permet de vérifier que l'algo fonctionne bien.
 */
public class MainPoint{
  
    private static double[] array(double x, double y){
      double[] t = {x,y};
      return t;
     }
  
    public static void main(String argv[]) throws ClusterException{
       Cluster lesDonnees ;
       Donnee[] lesCentres ;
      // on crée un  tableau pour les centres.
      lesCentres = new Donnee[3];
      lesCentres[0] = new Donnee(array(0.6,0.9));
      lesCentres[1] = new Donnee(array(1.0,0.9));
      lesCentres[2] = new Donnee(array(0.3,0.3));
      // on crée un cluster pour les données.
      lesDonnees = new Cluster(2); // chaque donnee a 2 dimensions
      lesDonnees.add(new Donnee(array(0.1,0.1)));
      lesDonnees.add(new Donnee(array(0.2,0.2)));
      lesDonnees.add(new Donnee(array(0.1,0.3)));
      lesDonnees.add(new Donnee(array(0.2,0.3)));
      lesDonnees.add(new Donnee(array(0.4,0.9)));
      lesDonnees.add(new Donnee(array(0.4,0.7)));
      lesDonnees.add(new Donnee(array(0.5,0.8)));
      lesDonnees.add(new Donnee(array(0.3,0.8)));
      lesDonnees.add(new Donnee(array(0.9,0.3)));
      lesDonnees.add(new Donnee(array(0.8,0.4)));
      lesDonnees.add(new Donnee(array(1.0,0.4)));
      lesDonnees.add(new Donnee(array(0.9,0.5)));
      // on applique l'algo pour avoir 3 clusters, avec des centres initialisés pour chaque cluster
      Clustering cl = new Clustering(3, lesDonnees, lesCentres);
      cl.algo(true) ; // ici on utilise la fonction comme une procedure
    }
  
}
