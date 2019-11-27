package Controllers;

/**
 * Represents a Customer object
 * <p>
 * Customer contains a users username, password, and location which
 * used to validate login information to proceed into the application
 *
 * @author Michal Kubiak
 */
public class Customer {
    /**
     * Each Customer contains a username String
     */
    private String username;
    /**
     * Each Customer contains a Password String
     */
    private String password;
    /**
     * Each Customer contains a Location String
     */
    private String location;

    /**
     * Creates a new Customer using the single argument constructor
     * within, and provides a default value for username, password and location
     * which are "Unknown"
     */
    public Customer() {
        setUsername("Unknown");
        setPassword("Unknown");
        setLocation("Unknown");
    }

    /**
     * Creates a new Customer with the given username, password and location
     * *
     *
     * @param username The username of the customer
     * @param password The password of the customer
     * @param location The location of the customer
     */
    public Customer(String username, String password, String location) {
        setUsername(username);
        setPassword(password);
        setLocation(location);
    }

    /**
     * Creates a new Customer with the given username, password and location
     * *
     *
     * @param username The username of the customer
     * @param password The password of the customer
     */
    public Customer(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    /**
     * Accessor ...
     *
     * @return username of customer
     */
    public String getUsername() {
        return username;
    }

    /**
     * Mutator ...
     *
     * @param username of the customer
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Accessor ...
     *
     * @return username of customer
     */
    public String getPassword() {
        return password;
    }

    /**
     * Mutator ...
     *
     * @param password of the customer
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Accessor ...
     *
     * @return username of customer
     */
    public String getLocation() {
        return location;
    }

    /**
     * Mutator ...
     *
     * @param location of the customer
     */
    public void setLocation(String location) {
        this.location = location;
    }


    /**
     * Returns the details of the Customer
     *
     * @return username, password, location of customer
     */
    @Override
    public String toString() {
        return "Customer{" +
                "username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", location='" + getLocation() + '\'' +
                '}';
    }


}
