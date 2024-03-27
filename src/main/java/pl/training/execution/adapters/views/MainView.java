package pl.training.execution.adapters.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.security.AuthenticationContext;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.userdetails.UserDetails;
import pl.training.execution.domain.Execution;
import pl.training.execution.domain.ExecutionService;

import java.text.NumberFormat;
import java.time.Month;
import java.util.Locale;

import static java.time.format.TextStyle.FULL_STANDALONE;

@RolesAllowed("ADMIN")
@PageTitle("Training management")
@Route("")
public class MainView extends VerticalLayout {

    private final ExecutionService executionService;
    private final AuthenticationContext authenticationContext;
    private final Grid<Execution> grid = new Grid<>();
    private final ExecutionForm executionForm = new ExecutionForm();

    public MainView(ExecutionService executionService,  AuthenticationContext authenticationContext) {
        this.executionService = executionService;
        this.authenticationContext = authenticationContext;
        setSizeFull();

        authenticationContext.getAuthenticatedUser(UserDetails.class)
                .ifPresent(userDetails -> {
                    var logoutButton = new Button("Logout " + userDetails.getUsername(), event -> authenticationContext.logout());
                    add(logoutButton);
                });

        add(initStatsPanel(), initMainPanel(), initTabsPanel());
    }

    private VerticalLayout initStatsPanel() {
        var panel = new VerticalLayout();

        panel.add(createInfo("Liczba szkoleń", 0));
        panel.add(createInfo("Liczba uczestników", 0));

        var incomePanel = new HorizontalLayout();
        incomePanel.add(
                createInfo("Dochód", 0.0),
                createInfo("Średni dochód / szkolenie", 0.0),
                createInfo("Średni dochód / dzień", 0.0)
        );
        panel.add(incomePanel);

        return panel;
    }

    private Component createInfo(String label, Number value) {
        var div = new Div();
        div.setText(label + ": "  + value);
        div.setClassName("info");
        return div;
    }

    private VerticalLayout initMainPanel() {
        var panel = new VerticalLayout();
        panel.setSizeFull();
        initGrid();
        panel.add(grid, executionForm);
        return panel;
    }

    private void initGrid() {
        grid.addColumn(Execution::getStartDate).setHeader("Data");
        grid.addColumn(execution -> execution.getTraining().getDurationInDays()).setHeader("Liczba dni");
        grid.addColumn(Execution::getStatus).setHeader("Termin");
        grid.addColumn(Execution::getOwner).setHeader("Opiekun");
        grid.addColumn(execution -> execution.getTraining().getCode()).setHeader("Kod szkolenia");
        grid.addColumn(execution -> execution.getTraining().getTitle()).setHeader("Tytuł szkolenia");
        grid.addColumn(execution -> formatCurrency(execution.getTraining().getPrice())).setHeader("Cena katalogowa");
        grid.addColumn(execution -> formatCurrency(execution.getCosts())).setHeader("Koszty");
        grid.addColumn(execution -> formatCurrency(execution.getIncome())).setHeader("Dochód");
        grid.addColumn(execution -> formatCurrency(execution.getIncomePerDay())).setHeader("Dochód / dzień");
        grid.addColumn(execution -> "0").setHeader("Potwierdzenia");
        grid.addColumn(execution -> "0").setHeader("Rezerwacje");
        grid.getColumns().forEach(column -> column.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> executionForm.setExecution(event.getValue()));
    }

    private String formatCurrency(double value) {
        var formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(value);
    }

    private void refreshExecutions(Month month) {
        grid.setItems(executionService.findByMonth(month));
    }

    private Tabs initTabsPanel() {
        var tabs = new Tabs();
        tabs.addSelectedChangeListener(event -> refreshExecutions(Month.valueOf(event.getSelectedTab().getId().get())));
        for (Month month : Month.values()) {
            var label = month.getDisplayName(FULL_STANDALONE, Locale.getDefault());
            var tab = new Tab(label);
            tab.setId(month.name());
            tabs.add(tab);
        }
        return tabs;
    }

}
