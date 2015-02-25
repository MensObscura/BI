package exemples ;
import clustering.*;
import java.io.*;

public class MainSquare{
  
    private static double[] array(double x, double y){
        double[] t = {x,y};
        return t;
    }
  
    private static Cluster lireFichier(String fichier) {
        String ligne;
        String[] ligneCoupee ;
        Cluster data = new Cluster(2) ;
        BufferedReader ficTexte;
        try {
            ficTexte = new BufferedReader(new FileReader(new File(fichier)));
            if (ficTexte == null) {
                throw new FileNotFoundException("Fichier non trouvé: " + fichier);
            }
            do {
                ligne = ficTexte.readLine();
                if (ligne != null) {
                    System.out.println(ligne);
                    if (ligne.charAt(0) != '@'){
                        ligneCoupee = ligne.split(",");
                        data.add(new Donnee(array(Double.parseDouble(ligneCoupee[0]),Double.parseDouble(ligneCoupee[1]))));
                    }
                }
            } while (ligne != null);
            ficTexte.close();
            System.out.println("\n");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ClusterException e){
            System.err.println(e.getMessage());
        }
        return data ;
    }

    private static void ecrireFichier(Cluster cl, String fichier) {
        String cheminFichier = System.getProperty("user.dir") + "/"+ fichier;
        try{
            BufferedWriter ficTexte = new BufferedWriter(new FileWriter(cheminFichier, false));
            for (Donnee d : cl){
                ficTexte.write(d.valeurDim(0)+" "+d.valeurDim(1)+"\n");
            }
            ficTexte.flush();				
            ficTexte.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
  
    public static void main(String argv[]) {
        if (argv.length < 2) {
            System.out.println("il faut deux parametres, le fichier et le nombre de clusters.");
            System.out.println("Par exemple : java -cp ./classes exemples.MainSquare square1.data 4") ;
        }else {
            Cluster lesDonnees = lireFichier(argv[0]) ;
            int k = Integer.parseInt(argv[1]) ;
            System.out.println("nombre de données lues : "+lesDonnees.size());
            // essayer avec d'autres distances
            Distance dist = new DistanceEuclidienne() ;
            Clustering cl = new Clustering(k, lesDonnees, dist);
            Cluster[] lesClusters = cl.algo(false) ;
            for (int i=0 ; i<k ; i++){
                System.out.println("le cluster "+i+" a "+lesClusters[i].size()+" donnees et son centre est "+ lesClusters[i].moyenne());
                ecrireFichier(lesClusters[i],i+".dat");
            }
            double wc = cl.wc() ;
            double bc = cl.bc() ;
            double rapport = bc/wc ;
            System.out.println("WC = "+wc+" BC = "+bc+" Rapport BC/WC = "+rapport);
        }
    }
  
}
