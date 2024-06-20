import java.util.Scanner;

public class QuizApplication {
    private static final int NUM_QUESTIONS = 5; // Number of questions in the quiz
    private static final String[][] QUESTIONS = {
            {"Which animal is known as the 'Ship of the Desert'?", "Camel", "Horse", "Elephant", "Lion", "1"},
            {"How many days are there in a week?", "5", "6", "7", "8", "3"},
            {"How many hours are there in a day?", "12 hours", "24 hours", "36 hours", "48 hours", "2"},
            {"How many letters are there in the English alphabet?", "24 letters", "25 letters", "26 letters", "27 letters", "3"},
            {"Rainbow consists of how many colours?", "5 colours", "6 colours", "7 colours", "8 colours", "3"}
    };

    private int score;

    public QuizApplication() {
        score = 0;
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz! Answer the following questions:");

        for (int i = 0; i < NUM_QUESTIONS; i++) {
            String[] currentQuestion = QUESTIONS[i];
            askQuestion(currentQuestion, scanner);
        }

        scanner.close();

        displayResult();
    }

    private void askQuestion(String[] question, Scanner scanner) {
        String questionText = question[0];
        String option1 = question[1];
        String option2 = question[2];
        String option3 = question[3];
        String option4 = question[4];
        String correctAnswer = question[5];

        System.out.println("\nQuestion: " + questionText);
        System.out.println("1. " + option1);
        System.out.println("2. " + option2);
        System.out.println("3. " + option3);
        System.out.println("4. " + option4);

        System.out.print("Your answer (enter option number): ");
        String userAnswer = scanner.nextLine();

        if (userAnswer.equals(correctAnswer)) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect! The correct answer was: " + correctAnswer);
        }
    }

    private void displayResult() {
        System.out.println("\nQuiz ended! Your score: " + score + "/" + NUM_QUESTIONS);
    }

    public static void main(String[] args) {
        QuizApplication quizApp = new QuizApplication();
        quizApp.startQuiz();
    }
}
