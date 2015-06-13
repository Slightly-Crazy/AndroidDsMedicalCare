package Events;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;


/**
 * Created by wendywang on 15-06-13.
 */
public class Bedtime extends Events {
    ArrayList<String> answers;
    private String question;
    private Time bedTime;


    public Bedtime(Time bedTime){
        super(bedTime);
        question = new String();
        answers = new ArrayList<String>();
    }

    public void setQuestion(String content){
        content = "How was your mood?";
        question = content;
    }

    public String getQuestion(){
        return question;
    }

    public void setAnswers(ArrayList<String> answers){
        answers.add("Happy");
        answers.add("Sad");
        answers.add("Moderate");
        answers.add("Excited");
        answers.add("Sleepy");
        answers.add("Tired");
        this.answers = answers;
    }

//    public void setButtons(){
//        RadioButton happy = new RadioButton("happy");
//
//    }

//    public String getAnswer(RadioGroup radioGroup, ArrayList<String> answers){
//
//        radioGroup.getCheckedRadioButtonId();
//        if (radioButton.isClickable() && radioButton.isChecked()) {
//            for (int i = 0; i < answers.size(); i++){
//                String item = answers.get(i);
//                if (item == radioButton.)
//            }
//        }
//
//
//
//    }







    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bedtime bedtime = (Bedtime) o;

        if (!question.equals(bedtime.question)) return false;
        return bedTime.equals(bedtime.bedTime);

    }

    @Override
    public int hashCode() {
        int result = question.hashCode();
        result = 31 * result + bedTime.hashCode();
        return result;
    }
}
