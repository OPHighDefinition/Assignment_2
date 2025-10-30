package assignment_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;


public class index {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanname = new Scanner(System.in);
        System.out.println("Hello please enter your name");
        String name = scanname.nextLine();
        Player player = new Player(name);

        System.out.println("Hello and welcome to Who Wants to Be a Millionaire?\n");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("Players are presented with questions increasing in diffculty.");
        System.out.println("You will be given four options to choose from (A, B, C, or D) With only One correct answer.\n");

        int moneytree[] = {100, 500, 1200, 2_500, 8_000, 32_000, 125_000, 250_000, 500_000, 1_000_000};
        //Used to count score
        int score = 0;
        // used in while loop 
        boolean run;
        boolean playing = true;
        // used for checking user input
        String a = "a", b = "b", c = "c", d = "d", A = "A", B = "B", C = "C", D = "D", y = "y", Y = "Y", n = "n", N = "N";

        char[] correctAnswersBank2 = {
            'B', 'A', 'A', 'B', 'C', 'C', 'C', 'B', 'D', 'B', 'D', 'D', 'C', 'A'
        };

        //Question number
        int QueNum = 1;

//Creates random number for picking questions 
        int[] randomNum = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Random random = new Random();

        for (int i = randomNum.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // Swap elements
            int temp = randomNum[i];
            randomNum[i] = randomNum[j];
            randomNum[j] = temp;

        }
        String[][] QuestionBank1 = {
            {"What is the capital of New Zealand\n", "A: Auckland", "B: Wellington", "C: Christchurch", "D: Hamilton"},
            {"What is the main ingredient in guacamole?\n", "A: Avocado", "B: Lemon", "C: Tomatoes", "D: Onion"},
            {"What is the name of the largest ocean on Earth?\n", "A: Pacific Ocean", "B: Indian Ocean", "C: Alantic Ocean", "D: Arctic Ocean"},
            {"In the story of Snow White, how many dwarfs are there?\n", "A: 5", "B: 7", "C: 8", "D: 12"},
            {"What do bees collect to make honey?\n", "A: Flowers", "B: Honey", "C: Nectar", "D: Pollen"},
            {"What is the name of Batman's butler?\n", "A: Seb", "B: Sebastion", "C: Alfred", "D: Fred"},
            {"How many Lord of the Rings films are there\n", "A: 2", "B: 5", "C: 3", "D: 4"},
            {"Which animal can be seen on the Porsche logo?\n", "A: Lion", "B: Horse", "C: Pony", "D: Tiger"},
            {"Which kind of alcohol is Russia notoriously known for?\n", "A: Rum", "B: Whisky", "C: Wine", "D: Vodka"},
            {"Which country is responsible for giving us pizza and pasta?\n", "A: America", "B: Italy", "C: Germany", "D: Spain"},
            {"Which element is said to keep bones strong?\n", "A: Iron", "B: Carbon", "C: Milk", "D: Calcium"},
            {"Which continent is the largest?\n", "A: Europe", "B: Australia", "C: Africa", "D: Asia"},
            {"Which country is known as the Land of the Rising Sun?\n", "A: England", "B: China", "C: Japan", "D: Russia"},
            {"Which warrior's weakness was their heel?\n", "A: Achilles", "B: Hermes", "C: Heracles", "D: Odysseus"}
        };
        String[][] QuestionBank2 = {
            {"What is the only organ that can regenerate?\n", "A: Kidney", "B: Liver", "C: Heart", "D: Brain"},
            {"Who is the Norse god of the wind and sea?\n", "A: Njord", "B: Thor", "C: Hades", "D: Orjani"},
            {"How many dimples does the average golf ball have?\n", "A: 336", "B: 298", "C: 367", "D: 347"},
            {"What's the Loudest Animal on Earth?\n", "A: Sperm whale", "B: Lion", "C: Hawk", "D: Elephant"},
            {"What's the Only Kind of Parrot That Can’t Fly?\n", "A: Rainbow Parrot", "B: Kiwi", "C: Kakapo", "D: Grey Parrot"},
            {"What Are People with Alektorophobia Afraid Of?\n", "A: Dogs", "B: Cats", "C: Chickens", "D: Cows"},
            {"What does a Geiger counter measure?\n", "A: Volts", "B: Light levels", "C: Radiation", "D: Oms"},
            {"What is the most abundant gas in the Earth's atmosphere?\n", "A: Co2", "B: Nitrogen", "C: Oxygen", "D: Hydrogen"},
            {"How long does it take for sunlight to reach Earth?\n", "A: 10 Minutes", "B: 30 Seconds", "C: 5 Minutes", "D: 8 Minutes"},
            {"What is the waste product of photosynthesis?\n", "A: Carbon Dioxide", "B: Oxygen", "C: Nitrogen", "D: Helium"},
            {"How many rings are in the Olympic logo?\n", "A: 4", "B: 7", "C: 6", "D: 5"},
            {"What is the diameter of a basketball hoop in inches?\n", "A: 17 inches.", "B: 15 inches.", "C: 22 inches.", "D: 18 inches."},
            {"Which country is known as the Land of the Rising Sun?\n", "A: England", "B: China", "C: Japan", "D: Russia"},
            {"How many moons does Saturn have\n", "A: 146", "B: 157", "C: 98", "D: 67"}
        };
        String[][] QuestionBank3 = {
            {"What is the chemical symbol for gold\n", "A: Gd", "B: Au", "C: Gb", "D: Ag"},
            {"What gas do plants absorb from the atmosphere?\n", "A: Carbon Dioxide", "B: Oxygen", "C: Methane", "D: Butane"},
            {"What is the smallest country in the world by land area\n", "A: vatican City", "B: New Zealand", "C: Greenland", "D: Fiji "},
            {"What is the hardest natural substance on Earth?\n", "A: Carbon", "B: Diamond", "C: Steel", "D: Water"}

        };

        // File I/O:
        try {
            FileOutputStream fos = new FileOutputStream("./resources/score.txt");
            PrintWriter pw = new PrintWriter(fos);

            FileReader fr = new FileReader("./resources/score.txt");
            BufferedReader inputStream = new BufferedReader(fr);
            String line = null;

            if (playing == true) {

                // Output the random question using nested loops 
                // for asking user Questions from bank one
                //______________________________________________________________________________________________
                for (int i = 0; i < 5; i++) { // Outer loop for rows

                    Scanner scanner = new Scanner(System.in);
                    System.out.println("\nQuestion " + QueNum + " ");
                    QueNum++;
                    int rand = randomNum[i];
                    for (int j = 0; j < QuestionBank1[i].length; j++) { // Inner loop for columns

                        System.out.print(QuestionBank1[rand][j] + " ");

                        System.out.println("");
                    }

                    System.out.println("\nPlease input answer");

                    String input;

                    input = scanner.nextLine();
                    run = true;
                    while (run == true) {
                        //Checks if input is correct
                        if (a.equals(input) || b.equals(input) || c.equals(input) || d.equals(input) || A.equals(input) || B.equals(input) || C.equals(input) || D.equals(input)) {

                            //Add code to check if question is Correct
                            switch (rand) {
                                case 0:
                                    //Correct answer : B
                                    if (b.equals(input) || B.equals(input)) {
                                        System.out.println("Correct!\n");
                                        score++;
                                    } else {
                                        System.out.println("Incorrect your final payout is $0:");
                                        return;

                                    }
                                    break;
                                case 1:
                                    //Correct answer : A
                                    if (a.equals(input) || A.equals(input)) {
                                        System.out.println("Correct!\n");
                                        score++;
                                    } else {
                                        System.out.println("Incorrect your final payout is $0:");
                                        return;
                                    }
                                    break;

                                case 2:
                                    //Correct answer : A
                                    if (a.equals(input) || A.equals(input)) {
                                        System.out.println("Correct!\n");
                                        score++;
                                    } else {
                                        System.out.println("Incorrect your final payout is $0:");
                                        return;
                                    }
                                    break;
                                case 3:
                                    //Correct answer : B
                                    if (b.equals(input) || B.equals(input)) {
                                        System.out.println("Correct!\n");
                                        score++;
                                    } else {
                                        System.out.println("Incorrect your final payout is $0:");
                                        return;
                                    }
                                    break;
                                case 4:
                                    //Correct answer : C
                                    if (c.equals(input) || C.equals(input)) {
                                        System.out.println("Correct!\n");
                                        score++;
                                    } else {
                                        System.out.println("Incorrect your final payout is $0:");
                                        return;
                                    }
                                    break;
                                case 5:
                                    //Correct answer : C
                                    if (c.equals(input) || C.equals(input)) {
                                        System.out.println("Correct!\n");
                                        score++;
                                    } else {
                                        System.out.println("Incorrect your final payout is $0:");
                                        return;
                                    }
                                    break;
                                case 6:
                                    //Correct answer : C
                                    if (c.equals(input) || C.equals(input)) {
                                        System.out.println("Correct!\n");
                                        score++;
                                    } else {
                                        System.out.println("Incorrect your final payout is $0:");
                                        return;
                                    }
                                    break;
                                case 7:
                                    //Correct answer : B
                                    if (b.equals(input) || B.equals(input)) {
                                        System.out.println("Correct!\n");
                                        score++;
                                    } else {
                                        System.out.println("Incorrect your final payout is $0:");
                                        return;
                                    }
                                    break;
                                case 8:
                                    //Correct answer : D
                                    if (d.equals(input) || D.equals(input)) {
                                        System.out.println("Correct!\n");
                                        score++;
                                    } else {
                                        System.out.println("Incorrect your final payout is $0:");
                                        return;
                                    }
                                    break;
                                case 9:
                                    //Correct answer :B
                                    if (b.equals(input) || B.equals(input)) {
                                        System.out.println("Correct!\n");
                                        score++;
                                    } else {
                                        System.out.println("Incorrect your final payout is $0:");
                                        return;
                                    }
                                    break;
                                case 10:
                                    //Correct answer : D
                                    if (d.equals(input) || D.equals(input)) {
                                        System.out.println("Correct!\n");
                                        score++;
                                    } else {
                                        System.out.println("Incorrect your final payout is $0:");
                                        return;
                                    }
                                    break;
                                case 11:
                                    //Correct answer : D
                                    if (d.equals(input) || D.equals(input)) {
                                        System.out.println("Correct!\n");
                                        score++;
                                    } else {
                                        System.out.println("Incorrect your final payout is $0:");
                                        return;
                                    }
                                    break;
                                case 12:
                                    //Correct answer : C
                                    if (c.equals(input) || C.equals(input)) {
                                        System.out.println("Correct!\n");
                                        score++;
                                    } else {
                                        System.out.println("Incorrect your final payout is $0:");
                                        return;
                                    }
                                    break;
                                case 13:
                                    //Correct answer : A
                                    if (a.equals(input) || A.equals(input)) {
                                        System.out.println("Correct!\n");
                                        score++;

                                    } else {
                                        System.out.println("Incorrect your final payout is $0:");
                                        return;
                                    }
                                    break;
                            }

                            run = false;
                        } else {
                            //If input if wrong print this:
                            System.out.println("\nPlease enter A, B, C or, D and try again");
                            input = scanner.nextLine();
                        }
                    }
                    //Checks if user wants to collect thier money and finish game
                    System.out.println("If you would like to collect your money now? Doing so will end the game");
                    System.out.println("To give your answer please enter 'y'/'n' \n");

                    String collect = scanner.nextLine();
                    run = true;
                    while (run == true) {
                        if (y.equals(collect) || Y.equals(collect) || n.equals(collect) || N.equals(collect)) {
                            if (y.equals(collect) || Y.equals(collect)) {
                                System.out.println("Congrats you have won  $" + moneytree[score] + " thank you for playing");
                                pw.println("\n" + name + moneytree[score]);
                                return;
                            } else if (n.equals(collect) || N.equals(collect)) {
                                run = false;
                            }
                        } else {
                            //If input if wrong print this:
                            System.out.println("\nPlease enter either y or n and try again");
                            collect = scanner.nextLine();
                        }
                    }
                }
                System.out.println("\nCongrats you reached a checkpoint!\n\n");

                // for asking user Questions from bank two
                //______________________________________________________________________________________________
                for (int i = 0; i < 5; i++) { // Outer loop for rows
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("\nQuestion " + QueNum + " ");
                    QueNum++;
                    int rand = randomNum[i];
                    for (int j = 0; j < QuestionBank2[i].length; j++) { // Inner loop for columns

                        System.out.print(QuestionBank2[rand][j] + " ");

                        System.out.println("");
                    }

                    System.out.println("\nPlease input answer");

                    String input;

                    input = scanner.nextLine();
                    run = true;
                    while (run == true) {
                        //Checks if input is correct
                        if (a.equals(input) || b.equals(input) || c.equals(input) || d.equals(input) || A.equals(input) || B.equals(input) || C.equals(input) || D.equals(input)) {

                            //Add code to check if question is correct
                            if (input.matches("[ABCD]")) {
                                if (input.charAt(0) == correctAnswersBank2[rand]) {
                                    System.out.println("Correct!\n");
                                    score++;
                                } else {
                                    System.out.println("Incorrect your final payout is $" + moneytree[score] + ":");
                                    return;
                                }
                                run = false;
                            } else {
                                System.out.println("\nPlease enter A, B, C or D and try again");
                                input = scanner.nextLine();
                            }

                            run = false;
                        } else {
                            //If input if wrong print this:
                            System.out.println("\nPlease enter A, B, C or, D and try again");
                            input = scanner.nextLine();
                        }
                    }
                    //Checks if user wants to collect thier money and finish game
                    System.out.println("If you would like to collect your money now? Doing so will end the game");
                    System.out.println("To give your answer please enter 'y'/'n' \n");

                    String collect = scanner.nextLine();
                    run = true;
                    while (run == true) {
                        if (y.equals(collect) || Y.equals(collect) || n.equals(collect) || N.equals(collect)) {
                            if (y.equals(collect) || Y.equals(collect)) {
                                System.out.println("Congrats you have won  $" + moneytree[score] + " thank you for playing");
                                pw.println("\n" + name + moneytree[score]);
                                return;
                            } else if (n.equals(collect) || N.equals(collect)) {
                                run = false;
                            }
                        } else {
                            //If input if wrong print this:
                            System.out.println("\nPlease enter either y or n and try again");
                            collect = scanner.nextLine();
                        }
                    }
                }
                //Checks if user wants to collect thier money and finish game
                Scanner scanner = new Scanner(System.in);
                System.out.println("If you would like to collect your money now? Doing so will end the game");
                System.out.println("To give your answer please enter 'y'/'n' \n");

                String collect = scanner.nextLine();
                run = true;
                while (run == true) {
                    if (y.equals(collect) || Y.equals(collect) || n.equals(collect) || N.equals(collect)) {
                        if (y.equals(collect) || Y.equals(collect)) {
                            System.out.println("Congrats you have won  $" + moneytree[score] + " thank you for playing");
                            pw.println("\n" + name + moneytree[score]);
                            return;
                        } else if (n.equals(collect) || N.equals(collect)) {
                            run = false;
                        }
                    } else {
                        //If input if wrong print this:
                        System.out.println("\nPlease enter either y or n and try again");
                        collect = scanner.nextLine();
                    }
                }

                // for asking user Questions from bank three
                System.out.println("\nBonus question:\n\n");
                //______________________________________________________________________________________________
                int rand = random.nextInt(3);
                for (int i = 0; i < QuestionBank2[i].length; i++) {
                    System.out.println(QuestionBank3[rand][i]);

                }

                String input = scanner.nextLine();
                run = true;
                while (run == true) {
                    //Checks if input is correct
                    if (a.equals(input) || b.equals(input) || c.equals(input) || d.equals(input) || A.equals(input) || B.equals(input) || C.equals(input) || D.equals(input)) {

                        switch (rand) {
                            case 0:
                                //Correct answer : B
                                if (b.equals(input) || B.equals(input)) {
                                    System.out.println("Correct! you win $3,000,000\n");
                                    score++;
                                } else {
                                    System.out.println("Nice try but that is incorrect!\n your final payout is $0");
                                    return;
                                }
                                break;
                            case 1:
                                //Correct answer : A
                                if (a.equals(input) || A.equals(input)) {
                                    System.out.println("Correct! you win $3,000,000\n");
                                    score++;
                                } else {
                                    System.out.println("Nice try but that is incorrect!\n your final payout is $0");
                                    return;
                                }
                                break;

                            case 2:
                                //Correct answer : A
                                if (a.equals(input) || A.equals(input)) {
                                    System.out.println("Correct! you win $3,000,000\n");
                                    score++;
                                } else {
                                    System.out.println("Nice try but that is incorrect!\n your final payout is $0");
                                    return;
                                }
                                break;
                            case 3:
                                //Correct answer : B
                                if (b.equals(input) || B.equals(input)) {
                                    System.out.println("Correct! you win $3,000,000\n");
                                    score++;
                                } else {
                                    System.out.println("Nice try but that is incorrect!\n your final payout is $0");
                                    return;

                                }
                                break;
                        }
                        run = false;
                    } else {
                        //If input wrong print this:
                        System.out.println("\nPlease enter A, B, C or, D and try again");
                        input = scanner.nextLine();
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error reading from file " + score);
        }

        //Winning/Losing message  
        System.out.println("Good game & thanks for playing");

    }

    public static boolean isValidAnswer(String answer) {
        if (answer.matches("[ABCD]")) {
            return true;
        } else {
            System.out.print("Invalid Input. Please your answer again: ");
            return false;
        }
    }
}
