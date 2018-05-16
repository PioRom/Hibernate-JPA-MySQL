package Models;

import Entities.Product;
import javafx.collections.FXCollections;
import org.codehaus.plexus.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SinglePartCollection {

    public List<SinglePart> makeSinglePartList(List<Product> inputProductList) {

        List<SinglePart> singleOrderCountList = FXCollections.observableArrayList();
        Map<Product, Integer> map = CollectionUtils.getCardinalityMap(inputProductList);
        for (Map.Entry<Product, Integer> entity : map.entrySet()) {

            SinglePart singlePart = new SinglePart();
            singlePart.setPriceForOne(entity.getKey().getPrice());
            singlePart.setProduct(entity.getKey());
            singlePart.setName(entity.getKey().getName());
            singlePart.setQuantityInBasket(entity.getValue());
            singlePart.setTotalValue(singlePart.getQuantityInBasket() * entity.getKey().getPrice());

            singleOrderCountList.add(singlePart);
        }
        return singleOrderCountList;
    }

    public List<SinglePart> delete(List<SinglePart> inputList, SinglePart singleOrder) {
        inputList.remove(singleOrder);
        return inputList;
    }


    public List<Product> getProductList(List<SinglePart> inputList) {

        Product productToList = null;
        List<Product> productList = new ArrayList<>();
        for(SinglePart singleOrder : inputList){
            for(int i=1;i<=singleOrder.getQuantityInBasket();i++){
                productList.add(singleOrder.getProduct());
            }
        }

        for (Product product : productList) {
            System.out.println(product.getName());
        }
        return productList;
    }


    public List<SinglePart> updateSinglePartList(List<SinglePart> inputList, SinglePart inputOrder){
        for(SinglePart order: inputList){
            if(inputOrder.getName().equals(order.getName())){
                inputList.remove(order);
                inputList.add(inputOrder);
            }
        }
        return inputList;
    }
}
