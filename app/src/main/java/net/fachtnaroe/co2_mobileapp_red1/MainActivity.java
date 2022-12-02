package net.fachtnaroe.co2_mobileapp_red1;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Clock;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.Web;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Form implements HandlesEventDispatching {
    private
    VerticalArrangement Main;
    HorizontalArrangement Main_h1;
    Label CO2Monitor, CO2, CO2Reading, Temperature, TemperatureReading, deviceLabel;
    Button PreviousCO2;
    TextBox deviceName;
    Web web_CELCIUS, web_CO2;
    Clock rolex;

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

        Main_h1=new HorizontalArrangement(Main);

        deviceLabel=new Label(Main_h1);
        deviceLabel.Text("Device:");
        deviceLabel.TextAlignment(Component.ALIGNMENT_NORMAL);
        deviceLabel.TextColor(COLOR_BLACK);
        deviceLabel.FontSize(25);
        deviceLabel.HeightPercent(10);
        deviceLabel.WidthPercent(40);
        deviceLabel.FontTypeface(TYPEFACE_SERIF);

        deviceName=new TextBox(Main_h1);
        deviceName.TextAlignment(ALIGNMENT_CENTER);
        deviceName.Hint("TCFE-CO2-20-AE");
        deviceName.Text("TCFE-CO2-20-AE");
        deviceName.FontSize(25);
        deviceName.HeightPercent(10);
        deviceName.WidthPercent(60);
        deviceName.FontTypeface(TYPEFACE_SERIF);

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
        rolex = new Clock(Main);
        web_CELCIUS =new Web(Main);
        rolex.TimerInterval(10000);
        rolex.TimerEnabled(true);

        EventDispatcher.registerEventForDelegation(this, formName, "Click");
        EventDispatcher.registerEventForDelegation(this, formName, "Timer");
        EventDispatcher.registerEventForDelegation(this, formName, "GotText");
        EventDispatcher.registerEventForDelegation(this, formName, "BackPressed");
        EventDispatcher.registerEventForDelegation(this, formName, "OtherScreenClosed");
    }
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {

        System.err.print("dispatchEvent: " + formName + " [" + component.toString() + "] [" + componentName + "] " + eventName);
        if (eventName.equals("BackPressed")) {
            // this would be a great place to do something useful
            return true;
        }
        else if (eventName.equals("Click")) {

        }
        else if (eventName.equals("GotText")) {
            dbg("GotText");
            if (component.equals(web_CELCIUS)) {
                String status = params[1].toString();
                String textOfResponse = (String) params[3];
                handleWebResponse(status, textOfResponse);
                return true;
            } else if (component.equals(web_CO2)) {
                String status = params[1].toString();
                String textOfResponse = (String) params[3];
                handleWebResponse(status, textOfResponse);
                return true;
            }
        }
        else if (eventName.equals("Timer")) {
            if (component.equals(rolex)) {
                rolex.TimerEnabled(false);

                web_CELCIUS.Url(
                        "https://fachtnaroe.net/qndco2?" +
                                "device=" + deviceName.Text() +
                                "&sensor=CELCIUS"
                );
                web_CELCIUS.Get();
                return true;
            }
        }
        return false;
    }
        else if (eventName.equals("Timer")) {
        if (component.equals(rolex)) {
            rolex.TimerEnabled(false);

            web_CELCIUS.Url(
                    "https://fachtnaroe.net/qndco2?" +
                            "device=" + deviceName.Text() +
                            "&sensor=CO2"
            );
            web_CO2.Get();
            return true;
        }
    }
        return false;
}
    void handleWebResponse(String status, String textOfResponse) {
        dbg(("<br><b>" + "some message here" + ":</b> " + textOfResponse + "<br>"));

        if (status.equals("200")) try {
            JSONObject parser = new JSONObject(textOfResponse);
            if (parser.getString("Status").equals("OK")) {
                TemperatureReading.Text(
                        parser.getString("value")
                );
                rolex.TimerEnabled(true);
            }
        }
        catch(JSONException e){

        }
    }
    public static void dbg (String debugMsg) {
        System.err.print( "~~~> " + debugMsg + " <~~~\n");
    }
}
// Here be monsters:
// put unwanted code here, or experimental code awaiting placement
https://fachtnaroe.net/qndco2device=&sensor=CELCIUS