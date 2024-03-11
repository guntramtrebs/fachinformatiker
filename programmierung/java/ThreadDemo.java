public class ThreadDemo {

        public static void main(String[] args) {
    
            // einfache Variante
            // benutze Unterklasse von Thread, dann ist es einfach
            threadAlsUnterklasseTest();
    
            // komplexere Variante
            // benutze Interface, wenn Unterklasse nicht möglich ist
            threadAlsInterfaceTest();
        }
    
        static void threadAlsUnterklasseTest() {
            System.out.println();
            System.out.println("Thread als Unterklasse Test startet");
            System.out.println();
    
            // Definition/Deklaration
            Thread thread1;
            Thread thread2;
    
            // Initialisierung
            thread1 = new ThreadAlsUnterklasse();
            thread2 = new ThreadAlsUnterklasse();
    
            //
            System.out.println("Vor den Threadstarts");
            thread1.start();
            thread2.start();
            System.out.println("Nach den Threadstarts");
    
    
            System.out.println("Warte auf Ende der Threads");
            try {
                thread1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                thread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Threads 1 und 2 beendet");
        }
    
        static void threadAlsInterfaceTest() {
            System.out.println();
            System.out.println("Thread als Interface Test startet");
            System.out.println();
    
            // Thread anlegen
            ThreadAlsInterface thread3 = new ThreadAlsInterface("thread3", 500, 3, "nicht parallel");
    
            // Methode starten, aber nicht als eigener Thread
            thread3.run();
    
            System.out.println("Thread1 wurde nicht parallel ausgeführt");
    
            // 2 Objekte vom Interfacetyp Runnable anlegen
            ThreadAlsInterface threadImplementation4 = new ThreadAlsInterface("thread4", 1000, 5, "läuft parallel");
            ThreadAlsInterface threadImplementation5 = new ThreadAlsInterface("thread5", 1500, 5, "läuft auch parallel");
    
            // 2 Threads anlegen
            Thread thread4 = new Thread(threadImplementation4);
            Thread thread5 = new Thread(threadImplementation5);
    
            thread4.start();
            thread5.start();
    
            System.out.println("Hauptthread läuft weiter");
            System.out.println("warte auf Ende von Thread 4 und 5");
    
            try {
                thread4.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                thread5.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    
            System.out.println("Thread 4 und 5 beendet");
    
            System.out.println();
            System.out.println("Parallel auf Variablen zugreifen ");
            System.out.println();
    
            // 2 Objekte vom Interfacetyp Runnable anlegen
            ThreadAlsInterface threadImplementation6 = new ThreadAlsInterface("thread6", 1000, 10, "10 oder 20 mal ausführen?");
    
            // 2 Threads anlegen
            Thread thread6 = new Thread(threadImplementation6);
            Thread thread7 = new Thread(threadImplementation6);
    
            thread6.start();
            thread7.start();
    
            System.out.println("Hauptthread ist beendet");
    
        }
    }
    
    
    class ThreadAlsUnterklasse extends Thread {
    
        @Override
        public void run() {
            System.out.println("Thread startet");
    
            // eine Sekunde warten
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread endet");
        }
    }
    
    // nur zur Demo, macht aber nichts
    class Elternklasse {
    
    }
    
    /*
    Oft erbt eine Klasse von einer Elternklasse, soll aber trotzdem parallel ausgeführt werden.
    Dann kann sie nicht von der Klasse Thread erben, sondern muss das Interface Runnable implementieren.
    
    Der parallele Aufruf geschieht dann durch Anlegen eines weiteren Threadobjekts, siehe main-Methode.
    
     */
    class ThreadAlsInterface extends Elternklasse implements Runnable {
    
        int schlafzeit;
    
        String nachricht;
    
        String name;
    
        int countDown;
    
        public ThreadAlsInterface(String name, int schlafzeit, int countDown, String nachricht) {
            this.name = name;
            this.schlafzeit = schlafzeit;
            this.countDown = countDown;
            this.nachricht = nachricht;
        }
    
        /*
        gibt die Nachricht countDown oft mal aus und wartet dazwischen sleepDuration Millisekunden
         */
        @Override
        public void run() {
            while (countDown>0) {
                System.out.println(this.name + " #" + this.countDown +  ": " + this.nachricht );
                countDown--;
                try {
                    //noinspection BusyWait
                    Thread.sleep(this.schlafzeit);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    