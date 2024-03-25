package pl.training.contacts.adapters.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;
import pl.training.MainLayout;
import pl.training.commons.PageConfig;
import pl.training.contacts.domain.Contact;
import pl.training.contacts.domain.ContactsManager;

@PageTitle("Contacts")
@RouteAlias(value = "", layout = MainLayout.class)
@Route(value = "contacts", layout = MainLayout.class)
public class ContactsView extends HorizontalLayout {

    private final ContactsManager contactsManager;

    @Autowired
    public ContactsView(ContactsManager contactsManager) {
        this.contactsManager = contactsManager;

        var pageConfig = new PageConfig(10, 0);
        var contacts = contactsManager.findAll(pageConfig).elements();

        var grid = new Grid<>(Contact.class);
        grid.setItems(contacts);

        add(grid);
    }

}
