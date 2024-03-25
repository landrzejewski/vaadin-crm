package pl.training.contacts.adapters.views;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import pl.training.contacts.domain.Contact;

public class ContactForm extends FormLayout {

    private final TextField firstName = new TextField("First name");

    public ContactForm() {
        add(firstName);
    }

    public void setContact(Contact contact) {
    }

}
