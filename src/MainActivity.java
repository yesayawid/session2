import Model.CartContents;
import Model.Shopper;
import network.ConnectURI;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class  MainActivity extends JFrame {
    private int _screenWidth;
    private int _screenHeight;
    private CartContents _listCart = new CartContents();
    ArrayList<CartContents> listCart = new ArrayList<>();
    DefaultListModel<String> modelList = new DefaultListModel();
    JList cartList = new JList();
    JPanel _cartPanel;

    public MainActivity() throws IOException { //conctraction
        getScreenSize();
        setScreenShow();
    }

    private void getScreenSize() {
        Toolkit _toolkit = Toolkit.getDefaultToolkit();
        Dimension _dimension = _toolkit.getScreenSize();
        this._screenHeight = _dimension.height;
        this._screenWidth = _dimension.width;
    }
    private void setScreenShow() throws IOException {
        this.setTitle("Shopper");
        this.setSize(this._screenWidth, this._screenHeight);
        this.setLayout(null);
        setTopPanel();
        setStokPanel();
        setCartPanel();
        setSortPanel();
        setSearchPanel();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void   setTopPanel() {
        JPanel _panelTop = new JPanel(new GridBagLayout());
        JLabel _labelTop = new JLabel();
        _labelTop.setText("Shopper. The Best Shopping Apps");
        Dimension _labelSize = _labelTop.getPreferredSize();

        _labelTop.setBounds(150, 100, _labelSize.width, _labelSize.height);
        _labelTop.setFont(new Font("Serif", Font.PLAIN, 24));
        _panelTop.setBounds(10, 20, this._screenWidth - 20, 38);

        _panelTop.add(_labelTop);
        this.add(_panelTop);
    }

    private void setStokPanel() throws IOException{
        Border _borderMiddle = BorderFactory.createTitledBorder("Select Product");
        JPanel _middlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        _middlePanel.setBounds(10, 70, this._screenWidth - 280, this._screenHeight);
        _middlePanel.setBorder(_borderMiddle);

        ArrayList<Shopper> _listItem = getProductData();
        Border[] _borderProducts = new Border[_listItem.size()];
        ImageIcon[] imageIcons = new ImageIcon[_listItem.size()];
        JButton[] _addButton = new JButton[_listItem.size()];
        JLabel[] _labelPic = new JLabel[_listItem.size()];
        JPanel[] _productPanel = new JPanel[_listItem.size()];
        JPanel[] _panelforText = new JPanel[_listItem.size()];
        JTextField[] _qtyField = new JTextField[_listItem.size()];


        for (int index = 0; index < _listItem.size(); index++) {
            String fileName = _listItem.get(index).get_filename();
            _borderProducts[index] = BorderFactory.createTitledBorder(_listItem.get(index).get_pluName());
            _productPanel[index] = new JPanel(new BorderLayout());
            _productPanel[index].setBackground(Color.WHITE);
            _productPanel[index].setBounds(150, 10, 80, 250);
            _productPanel[index].setBorder(_borderProducts[index]);
            _labelPic[index] = new JLabel("<html>Code : " + _listItem.get(index).ge t_pluCode() + "<br/>Name : " +
                    _listItem.get(index).get_pluName() + "<br/>Size : " +
                    _listItem.get(index).get_size() + "<br/>" +
                    _listItem.get(index).get_description() +
                    "<br/>Price : " + _listItem.get(index).get_price() + "<br/>");
            String _PATH = "C:\\Users\\ASUS\\IdeaProjects\\Projectjava\\image";
            imageIcons[index] = new ImageIcon(_PATH + fileName); //load the image to a imageIcon
            Image image1 = imageIcons[index].getImage(); //Imageicon.getImage(); //transform it
            Image newing1 = image1.getScaledInstance(120, 120, Image.SCALE_SMOOTH); //scale it the smooth way
            imageIcons[index] = new ImageIcon(newing1);
            _labelPic[index].setIcon(imageIcons[index]);
            _labelPic[index].setHorizontalTextPosition(JLabel.CENTER);
            _labelPic[index].setVerticalTextPosition(JLabel.BOTTOM);
            _labelPic[index].setBounds(10, 10, 40, 40);

            _panelforText[index] = new JPanel(new FlowLayout(FlowLayout.LEFT));
            _qtyField[index] = new JTextField();
            _qtyField[index].setText("1");
            _qtyField[index].setPreferredSize(new Dimension(60,30));

            _addButton[index] = new JButton();
            _addButton[index].setText("Add to chart");
            _addButton[index].setSize(new Dimension(100,30));
            int finalIndex = index;

            _addButton[index].addActionListener(e -> {
                CartContents myList = new CartContents();
                myList.setPluCode(_listItem.get(finalIndex).get_pluCode());
                myList.setPluName(_listItem.get(finalIndex).get_pluName());
                myList.setPluQty(Integer.parseInt(_qtyField[finalIndex].getText()));
                myList.setPluPrice(_listItem.get(finalIndex).get_price());
                listCart.add(myList);
                AddtoCart();
            });

            _panelforText[index].add(_qtyField[index]);
            _panelforText[index].add(_addButton[index]);
            _productPanel[index].add(_panelforText[index], BorderLayout.PAGE_END);
            _productPanel[index].add(_labelPic[index]);
            _middlePanel.add(_productPanel[index]);
        }
        this.add(_middlePanel);

    }
    public void AddtoCart(){
        cartList.setPreferredSize(new Dimension(230,420));
        cartList.setBackground(Color.LIGHT_GRAY);
        cartList.removeAll();
        modelList.removeAllElements();
        for (int index=0;index<listCart.size();index++){
            modelList.addElement(listCart.get(index).getPluCode().toString() + " - " + listCart.get(index).getPluName() + " - "
                    + listCart.get(index).getPluQty());
        }
        cartList.setModel(modelList);
        cartList.setSelectedIndex(2);
        _cartPanel.add(cartList);
        _cartPanel.setVisible(true);
        this.add(_cartPanel);
        this.revalidate();
    }
    public void setCartPanel(){
        Border _borderCart = BorderFactory.createTitledBorder("Your Cart");
        _cartPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        _cartPanel.setBounds(this. _screenWidth-260,70,260, this._screenHeight-300);
        _cartPanel.setBorder(_borderCart);
        this.add(_cartPanel);
    }
    public void setSortPanel(){
        Border _borderSort = BorderFactory.createTitledBorder("Sorting");
        JPanel _sortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        _sortPanel.setBounds(10,this._screenHeight-210,500,120);
        _sortPanel.setBorder(_borderSort);

        JButton[] _sortButton = new JButton[4];
        String[] _sortTitle = {"Quick","Bubble","Selection","Inserting"};

        for (int index = 0;index<_sortTitle.length;index++){
            _sortButton[index] = new JButton();
            _sortButton[index].setText(_sortTitle[index++]);
            _sortButton[index].setSize(new Dimension(100,30));
            _sortPanel.add(_sortButton[index]);
        }
        this.add(_sortPanel);
    }
    public void setSearchPanel(){
        Border _borderSearch = BorderFactory.createTitledBorder("Search");
        JPanel _searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        _searchPanel.setBounds(520,this._screenHeight,570,120);
        _searchPanel.setBorder(_borderSearch);
        JTextField _searchText = new JTextField();
        _searchText.setPreferredSize(new Dimension(500,30));
        _searchText.setToolTipText("Type keyword...");
        _searchPanel.add(_searchText);
        JButton[] _searchButton = new JButton[4];
        String[] _searchTitle = {"BST","Linear","Exponential","Jump"};

        for (int index=0;index<_searchTitle.length;index++){
            _searchButton[index] = new JButton();
            _searchButton[index].setText(_searchTitle[index]);
            _searchButton[index].setSize(new Dimension(100,30));
            _searchPanel.add(_searchButton[index]);
        }
        this.add(_searchPanel);
    }
    private JSONArray productsJSONArray;
    private ArrayList<Shopper> getProductData() throws IOException {
        URL sMe = ConnectURI.buildURL("https://java-api.mimoapps.xyz/getproducts.php");
        String productsResponse = ConnectURI.getResponseFromHttpUrl(sMe);
        assert productsResponse != null;
        JSONArray productJSONArray = new JSONArray(productsResponse);
        ArrayList<Shopper> productList = new ArrayList<>();
        for (int index = 0; index<productsJSONArray.length();index++){
            Shopper productsModel = new Shopper();
            JSONObject myJSONObject = productsJSONArray.getJSONObject(index);
            productsModel.set_pluCode(myJSONObject.getString("plucode"));
            productsModel.set_pluName(myJSONObject.getString("pluname"));
            productsModel.set_size(myJSONObject.getString("plusize"));
            productsModel.set_price(myJSONObject.getString("pluprice"));
            productsModel.set_description(myJSONObject.getString("pludesc"));
            productsModel.set_filename(myJSONObject.getString("filename"));

            productList.add(productsModel);
        }
        return productList;
    }

    public static void main(String[] args) throws IOException {
        MainActivity s = new MainActivity();

    }
}