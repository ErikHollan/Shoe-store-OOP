public class Shoe {

    int id;
    int size;
    String colour;
    int genderID;
    int brandID;
    int categoryID;
    int price;
    int quantity;

    public Shoe(int id, int size, String colour, int genderID, int brandID, int categoryID, int price, int quantity) {

        this.id = id;
        this.size = size;
        this.colour = colour;
        this.genderID = genderID;
        this.brandID = brandID;
        this.categoryID = categoryID;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public String getColour() {
        return colour;
    }

    public int getGenderID() {
        return genderID;
    }

    public String isGender(){

        String gender;
        if(genderID == 1){
            gender = "Female";
        }
        else if(genderID == 2){
            gender = "Male";
        }
        else{
            gender = "Humans are binary";
        }
        return gender;
    }

    public int getBrandID() {
        return brandID;
    }

    public String isBrand(){

        String brand = null;
        if (brandID == 1){
            brand = "Adidas";
        }
        else if(brandID == 2){
            brand = "Converse";
        }
        else if(brandID == 3){
            brand = "Ecco";
        }
        else if(brandID == 4){
            brand = "Nike";
        }
        else{
            brand = "Brand does not exist";
        }
        return brand;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String isCategory(){

        String category;
        if (categoryID == 1){
            category = "Dining";
        }
        else if(categoryID == 2){
            category = "Sneakers";
        }
        else if(categoryID == 3){
            category = "Sport";
        }
        else if(categoryID == 4){
            category = "Sandals";
        }
        else if(categoryID == 5){
            category = "Winter";
        }
        else{
            category = "Category does not exist";
        }
        return category;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int q){
        this.quantity = q;
    }

    @Override
    public String toString() {
        return "Shoe{" +
                "size=" + size +
                ", colour='" + colour + '\'' +
                ", gender=" + isGender() +
                ", brand=" + isBrand() +
                ", category=" + isCategory() +
                ", price=" + price +
                '}';
    }
}