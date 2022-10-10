import java.io.*;
import java.util.Random;


public class QuizSet{
    private final int SIZE = 5;
    private String[] qnsList = new String[SIZE];
    private String[] ansList = new String[SIZE];


    //CONSTRUCTOR
    public QuizSet(String qnPath, String ansPath)
    {
        //read QUESTIONS from files into array
        try {
            File oop_questions = new File(qnPath);
            BufferedReader questions = new BufferedReader(new FileReader(oop_questions));

            for (int i = 0; i < this.SIZE; i++) {
                this.qnsList[i] = questions.readLine();
            }
        }
        catch (IOException event)
        {   //if the file is not accessible
            for(int i = 0; i<SIZE; i++)
                this.qnsList[i] = (i*i) + " + " + (i+5) + "?";
        }

        //read ANSWERS from files into array
        try {
            File oop_answers = new File(ansPath);
            BufferedReader answers = new BufferedReader(new FileReader(oop_answers));

            for (int i = 0; i < this.SIZE; i++) {
                this.ansList[i] = answers.readLine();
            }
        }catch (IOException event){
            //if the file is not accessible
            for(int i = 0; i<SIZE; i++)
                this.ansList[i] = (i*i) + (i+5) + "";
        }

    }

    /****METHODS****/

    //return question by index
    public String getQuestion(int index)
    {
        return qnsList[index];
    }
    //return answer by index
    public String getAnswer(int index)
    {
        return ansList[index];
    }
    //return array size
    public int listLength()
    {
        return SIZE;
    }

}
