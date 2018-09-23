package com.example.game;
import java.util.Scanner;

public class Game {

    int totalvinstA;    //reserverar minne för variabler till antal vinster för spelare1 och spelare 2
    int totalvinstB;

    String spelareANamn= "Spelare1";    //deklarerar förvalda namn när spelarna inte har skrivit in sina namn
    String spelareBNamn= "Spelare2";

    int antalSpelare = 1;       //förvalda spelinställningar "default settings"
    int antalKast = 4;


    void visaSpelMeny() {       // metod för spelmeny
        System.out.println();
        System.out.println("(1) Inställningar ");       //utskrivning av menytext
        System.out.println("(2) Starta spelet");
        System.out.println("(3) Ledartavla");
        System.out.println("(4) Spelregler");
        System.out.println("(5) Exit game");
        System.out.print("Välj ett val: ");
    }

    void valSpelMeny(int optionSelected) {      //kör switch för  från spelmenyn
        switch (optionSelected) {
            case 1:
                this.startaNyttSpel();      //val startar metoden för tärningssimuleringen
                break;                         //bryter loopen
            case 2:
                this.spela();               //case val för att anroppa spela metoden
                break;
            case 3:
                this.whoIsLeading();
                break;
            case 4:
                this.visaSpelInst();
                break;
            default:
                break;
        }
    }

    void startaNyttSpel() {         //metod för ändring av spelinställningar


        Scanner scan = new Scanner(System.in);      //lägger in form av inmatningsmetod
        totalvinstA=0;      // återställer totalvinst räkningen för spelarna då nytt spel skapas
        totalvinstB=0;
        System.out.print("\nVänligen välj antal spelare, 1-2 : ");      // genom inmatning scanner så väljer
        antalSpelare = scan.nextInt();                                  // spelaren antal spelare via en int


        while (antalSpelare > 2 || antalSpelare < 0  ) {            // om variabeln inte ligger inom spannet 1-2
                                                                    // så visas felmedelande för användaren
            System.out.println("val är oväljbart, skriv in ett nummer från 1 to 2: ");
            antalSpelare = scan.nextInt();                          // som får mata in på nytt
        }

        System.out.println("Du har valt: " + antalSpelare + " spelare.");   //utmatning för vilka val spelaren valt
        scan.nextLine();            // denna kodrad behövs för att reseta inscanning från int till string
                                    // annars kan det bli fel i koden
        if (antalSpelare == 1 ){                                // ifsats för om 1 spelare är invald
            spelareBNamn = "Datorn";                            //här förväljs variablnamnet till datorn
            System.out.println("Skriv in spelare 1:s namn: ");  //och här får man välja sitt eget namn
            spelareANamn = scan.nextLine();
        }
        else if (antalSpelare == 2){                            //ifsats för om 2 spelare är invalda
            System.out.println("Skriv in spelare 1:s namn: ");
            spelareANamn = scan.nextLine();

            System.out.println("Skriv in spelare 2:s namn: ");
            spelareBNamn = scan.nextLine();
        }

        System.out.print("\nVänligen välj antal tärningskast : ");
        antalKast = scan.nextInt();
        System.out.println("Du har valt: " + antalKast + " kast.");

        scan.nextLine();





    }

    void spela() {
        int minSumma = 0;              // här deklareras var för "spelare1:s summa vilket jag döpt till minSumma
        int antalSidor = 6;            // här deklareras antal sidor för tärningen in som används i simulerigen
        if (antalSpelare== 1)
            spelareBNamn= "Datorn";
        for (int i = 1; i <= antalKast; i++) {              // for sats för simulering utav tärning som ska kastat ett förvalt antal gånger
            int mittKast = (int) (1 + antalSidor * Math.random());      //
            System.out.println( spelareANamn + "  kast " + i + " är " + mittKast);
            minSumma += mittKast;
        }
        int dinSumma = 0;
        for (int i = 1; i <= antalKast; i++) {
            int dittKast = (int) (1 + antalSidor * Math.random());
            if (antalSpelare == 1) {
                System.out.println(  spelareBNamn+ "  kast " + i + " är " + dittKast);
                dinSumma += dittKast;
            } else {
                System.out.println(   spelareBNamn + "  kast " + i + " är " + dittKast);
                dinSumma += dittKast;
            }
        }

        System.out.println("\n"  +spelareANamn + " summa är " + minSumma);      //här skrivs spelarnas summa ut
        if (antalSpelare == 1) {                                                //
            System.out.println("Datorns summa är " + dinSumma);
        } else {
            System.out.println(spelareBNamn+ " summa är " + dinSumma);
        }
        if (minSumma > dinSumma) {                                          // räknar ut vem av spelarna som vann genom if satser
            System.out.println("\n"+ spelareANamn + " vann!");
            totalvinstA++;                                                  // när en spelare vinner ökar totalvinsten
        } else if (minSumma < dinSumma) {                                   // för den respektive spelare
            if (antalSpelare == 1) {
                System.out.println("\nDatorn vann!");
                totalvinstB++ ;
            } else {
                System.out.println("\n" + spelareBNamn + " vann!");
                totalvinstB++;
            }
        } else if (minSumma == dinSumma) {                                  // om summan för båda spelarna är lika så skrivs det ut
            System.out.println("\nDet blev oavgjort!");

        }


        System.out.println();                   //radbryte
    }

    void whoIsLeading() {                       //anropning av metoden för att se vem som leder (highscore)


        int sumA = totalvinstA - totalvinstB;           // beräkning utav skilljepoäng
        int sumB = totalvinstB - totalvinstA;
        int[] vinster;                              // reserverar och skappar en array


        vinster = new int[2];                               // ställer in antalet rader i arrayen


        vinster[0] = totalvinstA;                           //deklarerar arrayraderna

        vinster[1] = totalvinstB;



        System.out.println("Ledartavla \n");        //information



        if (totalvinstA > totalvinstB) {                //kriterier för vilken ordning som informationen i arrayen ska skrivas ut

            System.out.println(spelareANamn + " Leder med: " + sumA + " antal vinster och har totalt: "     //utskrift
                    + vinster[0] + " antal vinster");

            System.out.println(spelareBNamn + " Ligger på andra plats och har totalt: "
                    + vinster[1] + " antal vinster");


        } else if (totalvinstA < totalvinstB){
            System.out.println(spelareBNamn + " Leder med: " + sumB + " antal vinster och har totalt: "
                    + vinster[1] + " antal vinster");

            System.out.println(spelareANamn + " Ligger på andra plats och har totalt: "
                    + vinster[0] + " antal vinster");


        }
        else {                      // om inget av ovan kriterier gäller skrivs det ut att båda spelarna ligger lika
            System.out.println(spelareANamn +" har "+ vinster[0] +  " antal vinster och ligger lika tillsammans med " + spelareBNamn + "  som har: "
                    + vinster[1] + " antal vinster");


        }

    }











    void visaSpelInst() {               // spelinstruktioner
        System.out.println("\n " +
                "Spelet går ut på att samla poäng via tärningskast av en sexsidig tärning. \n " +
                "Du kan välja att spela ensam mot datorn eller mot en annan spelare. \n " +
                "Spelaren med flest totala poäng när alla tärningskast har blivit kastade vinner!");

    }





    public static void main(String[] args) {                //main metod med välkomstmedelande
        System.out.println("Välkommen till tärningspelet!");

        Game game = new Game();                 //anroppar classen Game

        int optionSelected;

        while (true) {                             //skapar en oändlighets loop för att spelmenyn
            game.visaSpelMeny();
            System.out.println();
            Scanner sc = new Scanner(System.in);
            optionSelected = sc.nextInt();          // variabel för val vidspelmeny

            while (optionSelected > 5 || optionSelected < 0) {   //säkerställer att input valen är inom dom valbara restriktioner för menyvalen

                System.out.print("val är oväljbart, skriv in ett nummer från 1 to 5: ");
                optionSelected = sc.nextInt();
            }

            if (optionSelected == 5) {                  //val för att avsluta programet och bryta loopen
                System.out.println("Spelet avslutas");
                break;
            }

            game.valSpelMeny(optionSelected);           //anroppar tillbaka valmenyn


        }
    }
}


