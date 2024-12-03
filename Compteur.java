package Threads;

public class Compteur  extends Thread {
    private int max; 

    public Compteur(String nom) {
        super(nom); 
        this.max = 10; 
    }
    
    public Compteur(String nom, int max) {
        super(nom); 
        this.max = max; 
    }
    
    public void run() {
       
        for (int i = 1; i <= max; i++) {
            System.out.println(getName() + " : " + i); 

            try {
                
                Thread.sleep((int) (Math.random()*5000 ));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
       
        System.out.println(""+getName() + " a fini de compter jusqu'à " + max);
    }

    
    public static void main(String[] args) {
        
        Compteur compteur1 = new Compteur("CP1",11);
 
        
        Compteur compteur2 = new Compteur("CP2", 15);
        Compteur compteur3 = new Compteur("CP3", 8);

        compteur1.start(); 
        compteur2.start(); 
        compteur3.start(); 
    }


	
}
