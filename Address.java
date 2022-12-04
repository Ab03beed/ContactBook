package student.miun.abda2100;

public class Address {

    private String postAddress;
    private String postNumber;
    private String postTown;

    public void setPostAddress(String postAddress){
        this.postAddress = postAddress;
    }

    public String getPostAddress(){
        return postAddress;
    }

    public void setPostNumber(String postNumber){
        this.postNumber = postNumber;
    }

    public String getPostNumber(){
        return postNumber;
    }

    public void setPostTown(String postTown){
        this.postTown = postTown;
    }

    public String getPostTown(){
        return postTown;
    }

}