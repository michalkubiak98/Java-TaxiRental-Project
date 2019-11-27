package Controllers;

import javafx.beans.property.SimpleStringProperty;

public class Taxi {

    private final SimpleStringProperty id;
    private final SimpleStringProperty driver;
    private final SimpleStringProperty make;
    private final SimpleStringProperty Tlocation;
    private final SimpleStringProperty mobile;
    private final SimpleStringProperty rate;
    private final SimpleStringProperty days;


    public Taxi(String id, String driver, String make, String Tlocation, String mobile, String rate, String days) {
        this.id = new SimpleStringProperty(id);
        this.driver = new SimpleStringProperty(driver);
        this.make = new SimpleStringProperty(make);
        this.Tlocation = new SimpleStringProperty(Tlocation);
        this.mobile = new SimpleStringProperty(mobile);
        this.rate = new SimpleStringProperty(rate);
        this.days = new SimpleStringProperty(days);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public String getDriver() {
        return driver.get();
    }

    public void setDriver(String driver) {
        this.driver.set(driver);
    }

    public SimpleStringProperty driverProperty() {
        return driver;
    }

    public String getMake() {
        return make.get();
    }

    public void setMake(String make) {
        this.make.set(make);
    }

    public SimpleStringProperty makeProperty() {
        return make;
    }

    public String getTlocation() {
        return Tlocation.get();
    }

    public void setTlocation(String tlocation) {
        this.Tlocation.set(tlocation);
    }

    public SimpleStringProperty tlocationProperty() {
        return Tlocation;
    }

    public String getMobile() {
        return mobile.get();
    }

    public void setMobile(String mobile) {
        this.mobile.set(mobile);
    }

    public SimpleStringProperty mobileProperty() {
        return mobile;
    }

    public String getRate() {
        return rate.get();
    }

    public void setRate(String rate) {
        this.rate.set(rate);
    }

    public SimpleStringProperty rateProperty() {
        return rate;
    }

    public String getDays() {
        return days.get();
    }

    public void setDays(String days) {
        this.days.set(days);
    }

    public SimpleStringProperty daysProperty() {
        return days;
    }


}
