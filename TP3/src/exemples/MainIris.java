package exemples ;
import clustering.*;
import java.io.*;

public class MainIris{
  
    private static double[] array(double sl, double sw, double pl, double pw){
        double[] t = {sl,sw,pl,pw};
        return t;
    }
  
    private static Cluster lireFichier(String fichier) {
        String ligne;
        String[] ligneCoupee ;
        Cluster data = new Cluster(4) ;
        BufferedReader ficTexte;
        try {
            ficTexte = new BufferedReader(new FileReader(new File(fichier)));
            if (ficTexte == null) {
                throw new FileNotFoundException("Fichier non trouvé: " + fichier);
            }
            do {
                ligne = ficTexte.readLine();
                if (ligne != null && ligne.length() > 0) {
                    System.out.println(ligne);
                    if (ligne.charAt(0) != '@' && ligne.charAt(0) != '%'){
                        ligneCoupee = ligne.split(",");
                        data.add(new DonneeIris(array(Double.parseDouble(ligneCoupee[0]),
                                                      Double.parseDouble(ligneCoupee[1]),
                                                      Double.parseDouble(ligneCoupee[2]),
                                                      Double.parseDouble(ligneCoupee[3])),
                                                ligneCoupee[4]));
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
                ficTexte.write(d.valeurDim(0)+" "+d.valeurDim(1)+" "+d.valeurDim(2)+" "+d.valeurDim(3)+"\n");
            }
            ficTexte.flush();				
            ficTexte.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void afficheDetail(int i, Cluster cl){
        int nbSetosa = 0 ;
        int nbVersicolor = 0 ;
        int nbVirginica = 0 ;
        System.out.println("le cluster "+i+" a "+cl.size()+" donnees et son centre est "+ cl.moyenne());
        for (Donnee d : cl){
            if (((DonneeIris) d).nom().equals("Iris-setosa")) nbSetosa ++ ;
            else if (((DonneeIris) d).nom().equals("Iris-versicolor")) nbVersicolor++ ;
            else nbVirginica++ ;
        }
        System.out.println("--> Détail : "+nbSetosa+" iris setosa, "+nbVersicolor+" iris versicolor, "+nbVirginica+" iris virginica");
    }
  
   public static void main(String argv[]) {
        if (argv.length < 2) {
            System.out.println("il faut deux parametres, le fichier et le nombre de clusters.");
            System.out.println("Par exemple : java -cp ./classes exemples.MainIris iris.arff 3") ;
        }else {
            Cluster lesDonnees = lireFichier(argv[0]) ;
            int k = Integer.parseInt(argv[1]) ;
            System.out.println("nombre de données lues : "+lesDonnees.size());
            // essayer avec différentes distances
            Distance dist = new DistanceEuclidienne() ;
            Clustering cl = new Clustering(k, lesDonnees, dist);
            Cluster[] lesClusters = cl.algo(false) ;
            for (int i=0 ; i<k ; i++){
                afficheDetail(i,lesClusters[i]);
                ecrireFichier(lesClusters[i],i+".dat");
            }
            double wc = cl.wc() ;
            double bc = cl.bc() ;
            double rapport = bc/wc ;
            System.out.println("WC = "+wc+" BC = "+bc+" Rapport BC/WC = "+rapport);
        }
    }
  
}
