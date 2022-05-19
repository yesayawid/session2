package Model;

public class CartContents {
    private String pluCode;
    private String pluName;
    private int pluQty;
    private int pluPrice;

    public String getPluCode() { return pluCode; }

    public void setPluCode(String pluCode){this.pluCode = pluCode;}

    public String getPluName() { return  pluName; }
    public void setPluName(String pluName){this.pluName = pluName; }
    public int getPluQty() {return pluQty; }
    public void  setPluQty(int pluQty) { this.pluQty = pluQty; }

    public int getPluPrice() { return pluPrice; }
    public void setPluPrice(int pluPrice) { this.pluPrice = pluPrice; }

    public  CartContents(){

    }
}
