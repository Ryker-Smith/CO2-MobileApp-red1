package net.fachtnaroe.co2_mobileapp_red1;

import android.graphics.fonts.FontFamily;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Clock;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;

import java.util.Random;

public class MainActivity extends Form implements HandlesEventDispatching {
    private
    VerticalArrangement Main;
    Label Title, Instructions, Rules;
    TextBox InsertAnswer;
    Label HowManyGuessesItTook;
    Button Guess_Button, Restart;
    int RandomNumber;

    protected void $define() {
        /* this next allows the app to use the full screen.
        In fact, seemingly anything makes this work at 100%
        except "Fixed" and the this.Sizing instruction
        being absent in the first place. */
        /* Cur seo isteach. Is cuma cén focal atá ann, níl gá leis */
        this.Sizing("Responsive");

        Main= new VerticalArrangement(this);
        Main.BackgroundColor(COLOR_PINK);
        Main.HeightPercent(100);

        Title= new Label(Main);
        Title.Text("My Guessing Game!");
        Title.TextColor(COLOR_RED);
        Title.TextAlignment(ALIGNMENT_CENTER);
        Title.HeightPercent(10);
        Title.WidthPercent(100);
        Title.FontSize(30);
        Title.BackgroundColor(COLOR_PINK);
        Title.FontTypeface(TYPEFACE_SERIF);

        Instructions= new Label(Main);
        Instructions.Text("Guess a number then press Guess.");
        Instructions.TextColor(COLOR_BLACK);
        Instructions.TextAlignment(ALIGNMENT_CENTER);
        Instructions.HeightPercent(10);
        Instructions.WidthPercent(100);
        Instructions.FontSize(25);
        Instructions.FontTypeface(TYPEFACE_SERIF);

        Rules= new Label(Main);
        Rules.Text("Too high/low/correct?");
        Rules.TextColor(COLOR_BLACK);
        Rules.TextAlignment(ALIGNMENT_CENTER);
        Rules.HeightPercent(10);
        Rules.WidthPercent(100);
        Rules.FontSize(25);
        Rules.FontTypeface(TYPEFACE_SERIF);

        InsertAnswer= new TextBox(Main);
        InsertAnswer.HeightPercent(10);
        InsertAnswer.WidthPercent(100);
        InsertAnswer.BackgroundColor(COLOR_WHITE);
//        InsertAnswer.Text("");
        InsertAnswer.Hint("Insert answer here:");
        InsertAnswer.NumbersOnly(true);
        InsertAnswer.TextColor(COLOR_BLACK);
        InsertAnswer.FontSize(22);
        InsertAnswer.TextAlignment(ALIGNMENT_CENTER);

        Guess_Button= new Button(Main);
        Guess_Button.Text("GUESS!!");
        Guess_Button.TextColor(COLOR_BLACK);
        Guess_Button.TextAlignment(ALIGNMENT_CENTER);
        Guess_Button.HeightPercent(10);
        Guess_Button.WidthPercent(100);
        Guess_Button.FontSize(22);
        Guess_Button.BackgroundColor(COLOR_GRAY);

        HowManyGuessesItTook= new Label(Main);
        HowManyGuessesItTook.HeightPercent(10);
        HowManyGuessesItTook.WidthPercent(100);
        HowManyGuessesItTook.BackgroundColor(COLOR_WHITE);
        HowManyGuessesItTook.Text("0");
        HowManyGuessesItTook.TextAlignment(ALIGNMENT_CENTER);
        HowManyGuessesItTook.TextColor(COLOR_BLACK);
        HowManyGuessesItTook.FontSize(22);

        Restart= new Button(Main);
        Restart.Text("Restart?");
        Restart.TextColor(COLOR_BLACK);
        Restart.TextAlignment(ALIGNMENT_CENTER);
        Restart.HeightPercent(10);
        Restart.WidthPercent(100);
        Restart.FontSize(22);
        Restart.BackgroundColor(COLOR_GRAY);

        RandomNumber= new Random().nextInt(100);
        // now, the events the components can respond to
        EventDispatcher.registerEventForDelegation(this, formName, "Click");
        EventDispatcher.registerEventForDelegation(this, formName, "Timer");
    }

    @Override
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {
        // finally, here is how the events are responded to
        dbg("dispatchEvent: " + formName + " [" +component.toString() + "] [" + componentName + "] " + eventName);
        String msg;
        dbg("C");
        if (eventName.equals("Click")) {
            if (component.equals(Guess_Button)) {
                dbg("A");
                Integer temp = Integer.parseInt(HowManyGuessesItTook.Text());
                temp++;
                HowManyGuessesItTook.Text(temp.toString());
                if (isNumeric( InsertAnswer.Text())) {
                    int val = Integer.parseInt(InsertAnswer.Text());
                    dbg(InsertAnswer.Text() + " " + RandomNumber);
                    if (val > RandomNumber) {
                        // too high
                        msg = "Too high";
                    } else if (val < RandomNumber) {
                        // too low
                        msg = "Too low";
                    } else { // correct
                        msg = "Correct";
                    }
                    Rules.Text(msg);
                }
                else {
                    // display "That's not a number." on screen
                    dbg("B");
                }
                dbg("D");
                return true;
            }

            dbg("E");
        }
        return false;
    }
    public static void dbg (String debugMsg) {
        System.err.println( "~~~> " + debugMsg + " <~~~\n");
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
// Here be monsters:
// put unwanted code here, or experimental code awaiting placement