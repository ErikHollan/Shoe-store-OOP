public class Feedback {
    int id;
    String commment;
    int ratingId;
    int shoeId;

    Feedback (int id, String comment, int ratingId, int shoeId){
        this.id = id;
        this.commment = comment;
        this.ratingId = ratingId;
        this.shoeId = shoeId;
    }

    public int getId() {
        return id;
    }

    public String getCommment() {
        return commment;
    }

    public int getRatingId() {
        return ratingId;
    }

    public int getShoeId() {
        return shoeId;
    }
}
