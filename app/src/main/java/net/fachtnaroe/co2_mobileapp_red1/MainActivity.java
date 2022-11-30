package net.fachtnaroe.co2_mobileapp_red1;

import android.graphics.fonts.FontFamily;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Clock;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Image;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;

import java.util.Random;

public class MainActivity extends Form implements HandlesEventDispatching {
    private
    VerticalArrangement Main;
    Label CO2Monitor, CO2, CO2Reading, Temperature, TemperatureReading;
    Button PreviousCO2;

    protected void $define() {
        /* this next allows the app to use the full screen.
        In fact, seemingly anything makes this work at 100%
        except "Fixed" and the this.Sizing instruction
        being absent in the first place. */
        /* Cur seo isteach. Is cuma cén focal atá ann, níl gá leis */
        this.Sizing("Responsive");

        Main = new VerticalArrangement(this);
        Main.HeightPercent(100);
        Main.Image("cowbackground.jpg");

        CO2Monitor = new Label(Main);
        CO2Monitor.Text("CO2 MONITOR");
        CO2Monitor.TextColor(COLOR_BLACK);
        CO2Monitor.TextAlignment(ALIGNMENT_CENTER);
        CO2Monitor.HeightPercent(8);
        CO2Monitor.WidthPercent(100);
        CO2Monitor.FontSize(35);
        CO2Monitor.FontTypeface(TYPEFACE_SERIF);
        CO2Monitor.FontBold(true);

        CO2 = new Label(Main);
        CO2.Text("CO2 (parts per million-ppm):");
        CO2.TextColor(COLOR_BLACK);
        CO2.TextAlignment(ALIGNMENT_CENTER);
        CO2.HeightPercent(10);
        CO2.WidthPercent(100);
        CO2.FontSize(30);
        CO2.FontTypeface(TYPEFACE_SERIF);

        CO2Reading = new Label(Main);
        CO2Reading.Text("334");
        CO2Reading.TextColor(COLOR_BLACK);
        CO2Reading.TextAlignment(ALIGNMENT_CENTER);
        CO2Reading.HeightPercent(10);
        CO2Reading.WidthPercent(100);
        CO2Reading.FontSize(30);
        CO2Reading.BackgroundColor(COLOR_WHITE);
        CO2Reading.FontTypeface(TYPEFACE_SERIF);

        Temperature = new Label(Main);
        Temperature.Text("Temperature (degrees celcius):");
        Temperature.TextColor(COLOR_BLACK);
        Temperature.TextAlignment(ALIGNMENT_CENTER);
        Temperature.HeightPercent(10);
        Temperature.WidthPercent(100);
        Temperature.FontSize(30);
        Temperature.FontTypeface(TYPEFACE_SERIF);

        TemperatureReading = new Label(Main);
        TemperatureReading.HeightPercent(10);
        TemperatureReading.WidthPercent(100);
        TemperatureReading.Text("17");
        TemperatureReading.TextAlignment(ALIGNMENT_CENTER);
        TemperatureReading.TextColor(COLOR_BLACK);
        TemperatureReading.FontSize(30);
        TemperatureReading.BackgroundColor(COLOR_WHITE);
        TemperatureReading.FontTypeface(TYPEFACE_SERIF);

        PreviousCO2 = new Button(Main);
        PreviousCO2.Text("Previous CO2 Readings");
        PreviousCO2.TextColor(COLOR_BLACK);
        PreviousCO2.TextAlignment(ALIGNMENT_CENTER);
        PreviousCO2.HeightPercent(10);
        PreviousCO2.WidthPercent(100);
        PreviousCO2.FontSize(25);
        PreviousCO2.BackgroundColor(COLOR_GRAY);
        PreviousCO2.FontTypeface(TYPEFACE_SERIF);
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