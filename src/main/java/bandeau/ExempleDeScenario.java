package bandeau;

public class ExempleDeScenario {

    /**
     * "Programme principal" : point d'entrée d'exécution
     *
     * @param args les "arguments de ligne de commande", transmis au lancement du programme
     */
    public static void main(String[] args) {
        String message;
        if (args.length > 0)
        {
            message = args[0];
        } else {
            message = "Démonstration du bandeau";
        }

        Scenario s = new Scenario();
        s.addEffect(new RandomEffect(message, 500), 1);
        s.addEffect(new TeleType("Je m'affiche caractère par caractère", 100), 1);
        s.addEffect(new Blink("Je clignote 10x", 100), 10);
        s.addEffect(new Zoom("Je zoome", 50), 1);
        s.addEffect(new FontEnumerator(10), 1);
        s.addEffect(new Rainbow("Comme c'est joli !", 30), 1);
        s.addEffect(new Rotate("2 tours à droite", 180, 400, true), 2);

        BandeauVerrouillable b1 = new BandeauVerrouillable(s);
        BandeauVerrouillable b2 = new BandeauVerrouillable(s);

        Thread t1 = new Thread(b1);
        Thread t2 = new Thread(b2);

        System.out.println("CTRL+C pour terminer le programme");

        t1.start();
        t2.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {}

        s.addEffect(new Rotate("2 tours à gauche", 180, 4000, false), 2);

        s.playOn(b1);

    }

}