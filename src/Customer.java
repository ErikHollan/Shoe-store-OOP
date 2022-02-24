public class Customer {

    int id;
    String name;
    String street;
    String city;

    Customer(int id, String name, String street, String city) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }
}
