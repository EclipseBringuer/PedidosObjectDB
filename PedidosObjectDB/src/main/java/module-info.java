module com.cesur.pedidoshibernate {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires javax.persistence;
    requires java.sql;

    opens com.cesur.pedidoshibernate.controllers to javafx.fxml;
    opens com.cesur.pedidoshibernate to javafx.fxml;
    opens com.cesur.pedidoshibernate.domain.entities.user;
    opens com.cesur.pedidoshibernate.domain.entities.order;
    opens com.cesur.pedidoshibernate.domain.entities.item;
    opens com.cesur.pedidoshibernate.domain.entities.product;

    exports com.cesur.pedidoshibernate;
    exports com.cesur.pedidoshibernate.controllers;
}