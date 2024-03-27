package pl.training;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed("ADMIN")
@Route
public class HelloView extends VerticalLayout {

    public HelloView() {
        var msg = new Paragraph("");
        msg.setId("msg");
        var button = new Button("Click me", e -> msg.setText("Clicked!"));
        add(button, msg);
    }

}
