package pl.training.contacts.adapters.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;
import pl.training.MainLayout;
import pl.training.commons.PageConfig;
import pl.training.contacts.adapters.views.ContactForm.DeleteEvent;
import pl.training.contacts.adapters.views.ContactForm.SaveEvent;
import pl.training.contacts.domain.Contact;
import pl.training.contacts.domain.ContactsManager;

@PageTitle("Contacts")
@RouteAlias(value = "", layout = MainLayout.class)
@Route(value = "contacts", layout = MainLayout.class)
public class ContactsView extends VerticalLayout {

    private final Grid<Contact> grid = new Grid<>();
    private final ContactForm form = new ContactForm();
    private final ContactsManager contactsManager;

    @Autowired
    public ContactsView(ContactsManager contactsManager) {
        this.contactsManager = contactsManager;
        initGrid();
        initForm();
        refreshData();
        add(getToolbar(), getContent());
    }

    private Component getToolbar() {
        var createContactButton = new Button("Create");
        createContactButton.addClickListener(click -> addContact());
        return new HorizontalLayout(createContactButton);
    }

    private HorizontalLayout getContent() {
        var content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setSizeFull();
        return content;
    }

    private void initGrid() {
        grid.addColumn(Contact::getFirstName).setHeader("First name");
        grid.addColumn(Contact::getLastName).setHeader("Last name");
        grid.addColumn(Contact::getEmail).setHeader("Email");
        grid.addColumn(Contact::getCity).setHeader("City");
        grid.getColumns().forEach(column -> column.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> onSelectionChange(event.getValue()));
    }

    private void initForm() {
        form.setVisible(false);
        form.addSaveListener(this::saveContact);
        form.addDeleteListener(this::deleteContact);
        form.addCloseListener(event -> form.setVisible(false));
    }

    private void saveContact(SaveEvent event) {
        contactsManager.save(event.getContact());
        closeEditor();
        refreshData();
    }

    private void deleteContact(DeleteEvent event) {
        contactsManager.deleteById(event.getContact().getId());
        closeEditor();
        refreshData();
    }

    private void addContact() {
        grid.asSingleSelect().clear();
        onSelectionChange(new Contact());
    }

    private void onSelectionChange(Contact contact) {
        if (contact == null) {
            closeEditor();
        } else {
            form.setContact(contact);
            form.setVisible(true);
        }
    }

    private void closeEditor() {
        form.setVisible(false);
    }

    private void refreshData() {
        var pageConfig = new PageConfig(0, 100);
        var contacts = contactsManager.findAll(pageConfig).elements();
        grid.setItems(contacts);
    }

}
