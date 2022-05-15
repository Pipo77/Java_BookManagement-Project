public class Book {
    // attributes id, name, price
    int id;
    String name;
    double price;

    // constructor
    public Book(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * return this as a String in the required format
     */
    @Override
    public String toString() {
        String result = String.format("% 5d %-45s %10.2f",this.id,this.name,this.price);
        return result;
    }
}
