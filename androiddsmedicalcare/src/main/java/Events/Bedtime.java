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

    public String getQuestion(){
        return question;
    }

//    public String getAnswer(RadioGroup radioGroup, ArrayList<String> answers){
//
//
//
//
//    }
//






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
