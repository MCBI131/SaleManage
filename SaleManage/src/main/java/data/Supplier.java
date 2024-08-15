package data;

public class Supplier {
    private int supplierId;
    private String name_supplier;
    private String contactInfo;

    public Supplier(int supplierId, String name, String contactInfo) {
        this.supplierId = supplierId;
        this.name_supplier = name;
        this.contactInfo = contactInfo;
    }

    // Getters và Setters
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name_supplier;
    }

    public void setName(String name) {
        this.name_supplier = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
