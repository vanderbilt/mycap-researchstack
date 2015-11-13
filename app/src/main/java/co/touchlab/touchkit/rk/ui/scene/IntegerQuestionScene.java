package co.touchlab.touchkit.rk.ui.scene;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import co.touchlab.touchkit.rk.R;
import co.touchlab.touchkit.rk.common.answerformat.IntegerAnswerFormat;
import co.touchlab.touchkit.rk.common.result.QuestionResult;
import co.touchlab.touchkit.rk.common.result.StepResult;
import co.touchlab.touchkit.rk.common.step.QuestionStep;
import co.touchlab.touchkit.rk.common.step.Step;

public class IntegerQuestionScene extends Scene
{

    public IntegerQuestionScene(Context context, Step step)
    {
        super(context, step);
    }

    @Override
    public View onCreateBody(LayoutInflater inflater, ViewGroup parent)
    {
        QuestionResult<Integer> stringResult = (QuestionResult<Integer>)
                getStepResult().getResultForIdentifier(getStep().getIdentifier());;

        NumberPicker numberPicker = (NumberPicker) inflater.inflate(R.layout.item_number_picker,
                                                                    null);
        IntegerAnswerFormat answerFormat = (IntegerAnswerFormat) ((QuestionStep) getStep()).getAnswerFormat();

        numberPicker.setMinValue(answerFormat.getMinValue());
        numberPicker.setMaxValue(answerFormat.getMaxValue());

        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            QuestionResult<Integer> questionResult = new QuestionResult<Integer>(
                    getStep().getIdentifier());
            questionResult.setAnswer(newVal);
            setStepResult(questionResult);
        });

        if (stringResult != null)
        {
            numberPicker.setValue(stringResult.getAnswer());
        }

        return numberPicker;
    }

    @Override
    public StepResult createNewStepResult(String stepIdentifier)
    {
        return new StepResult<QuestionResult<String>>(stepIdentifier);
    }
}