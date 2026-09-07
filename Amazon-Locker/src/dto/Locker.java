package dto;


public class Locker {
    String name;

    String zipCode;

    public Locker(String namee , String _zipCode) {
        name = namee;
        zipCode = _zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


