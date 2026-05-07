module org.gentleninja.week3employeems {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.gentleninja.week3employeems to javafx.fxml;
    opens org.gentleninja.week3employeems.model to javafx.base;
    opens org.gentleninja.week3employeems.controller to javafx.fxml;

    exports org.gentleninja.week3employeems;
}