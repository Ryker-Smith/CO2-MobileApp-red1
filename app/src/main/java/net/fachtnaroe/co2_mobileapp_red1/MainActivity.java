package net.fachtnaroe.co2_mobileapp_red1;

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
import com.google.appinventor.components.runtime.PasswordTextBox;
import com.google.appinventor.components.runtime.TableArrangement;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;
import com.google.appinventor.components.runtime.Web;
import com.google.appinventor.components.runtime.WebViewer;
import com.google.appinventor.components.runtime.util.TimerInternal;

import java.io.Console;

public class MainActivity extends Form implements HandlesEventDispatching {

    private
    HorizontalArrangement HorizontalArrangement1;
    VerticalArrangement VerticalArrangement1;
    TextBox usernameBox;
    PasswordTextBox passwordBox;

    Button goButton;

    protected void $define() {

        HorizontalArrangement1=new HorizontalArrangement(this);
        HorizontalArrangement1.WidthPercent(100);

        VerticalArrangement1 = new VerticalArrangement(HorizontalArrangement1);
        usernameBox=new TextBox(VerticalArrangement1);
        usernameBox.FontSize(14);
        passwordBox=new PasswordTextBox(VerticalArrangement1);
        goButton = new Button(HorizontalArrangement1);
        goButton.Text("Hello");

        EventDispatcher.registerEventForDelegation(this, formName, "BackPressed");
        EventDispatcher.registerEventForDelegation(this, formName, "Click");
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {

        System.err.print("dispatchEvent: " + formName + " [" + component.toString() + "] [" + componentName + "] " + eventName);
        if (eventName.equals("BackPressed")) {
            // this would be a great place to do something useful

            return true;
        }
        else if (eventName.equals("Click")) {
            if (component.equals(goButton)) {
                goButton.Text("Goodbye");
                System.err.print("You pressed a button");
                // invert te timer status
                return true;
            }
        }
        else if (eventName.equals("Timer")) {
        }
        // true means event has been handled by us (ie recognised)
        return false;
    }
}