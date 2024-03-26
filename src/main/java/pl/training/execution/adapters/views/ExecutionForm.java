package pl.training.execution.adapters.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import pl.training.execution.domain.Execution;
import pl.training.execution.domain.ExecutionStatus;

public class ExecutionForm extends VerticalLayout {

    private final FormLayout formLayout = new FormLayout();
    private final DatePicker startDate = new DatePicker("Data");
    private final NumberField duration = new NumberField("Liczba dni");
    private final ComboBox<ExecutionStatus> status = new ComboBox<>("Termin");
    private final TextField owner = new TextField("Opiekun");
    private final TextField code = new TextField("Kod szkolenia");
    private final TextField title = new TextField("Tytuł szkolenia");
    private final NumberField price = new NumberField("Cena katalogowa");
    private final NumberField costs = new NumberField("Koszty");

    private final Button save = new Button("Zapisz");

    private BeanValidationBinder<Execution> binder = new BeanValidationBinder<>(Execution.class);

    private Execution execution;

    public ExecutionForm() {
        duration.setMin(1);
        duration.setMax(5);
        status.setItems(ExecutionStatus.values());
        formLayout.setResponsiveSteps(new ResponsiveStep("0", 2), new ResponsiveStep("500px", 8));
        formLayout.add(startDate, duration, status, owner, code, title, price, costs);
        add(formLayout);

        var buttons = new HorizontalLayout(save);
        buttons.setWidthFull();
        buttons.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        add(buttons);

        binder.bindInstanceFields(this);
        binder.forField(code)
                .bind(e -> e.getTraining().getCode(), (e, code) -> e.getTraining().setCode(code));
        binder.forField(title)
                .bind(e -> e.getTraining().getTitle(), (e, title) -> e.getTraining().setTitle(title));
        binder.forField(duration)
                .bind(e -> (double) e.getTraining().getDurationInDays(), (e, duration) -> e.getTraining().setDurationInDays(duration.intValue()));
        binder.forField(price)
                .bind(e -> e.getTraining().getPrice(), (e, price) -> e.getTraining().setPrice(price));
    }

    public void setExecution(Execution execution) {
        binder.readBean(execution);
    }

}
