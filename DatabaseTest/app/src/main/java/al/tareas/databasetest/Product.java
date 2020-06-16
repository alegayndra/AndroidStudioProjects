package al.tareas.databasetest;

public class Product {

    private int _id;
    private String _productName;
    private int _quantity;

    public Product() {

    }

    public Product(int id, String productName, int quantity) {
        this._id = id;
        this._productName = productName;
        this._quantity = quantity;
    }

    public Product(String productName, int quantity) {
        this._productName = productName;
        this._quantity = quantity;
    }

    // Setters ------------------------------------------------------------------------------

    public void setID(int id) {
        this._id = id;
    }

    public void setProductName(String productName) {
        this._productName = productName;
    }

    public void setQuantity(int quantity) {
        this._quantity = quantity;
    }

    // Getters ------------------------------------------------------------------------------

    public int getID() {
        return _id;
    }

    public String getProductName() {
        return _productName;
    }

    public int getQuantity() {
        return _quantity;
    }
}
