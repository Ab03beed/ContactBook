package student.miun.abda2100;

public class Person {
    private String firstName;
    private String lastName;
    private String signature;
    private int length;
    private Address address;

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setSignature(String signature){ this.signature = signature; }
    public String getSignature(){ return signature; }

    public void setLength(int length){
        this.length = length;
    }

    public int getLength(){
        return length;
    }

    public void setAddress(Address address){
        this.address = address;
    }

    public Address getAddress(){
        return address;
    }

}