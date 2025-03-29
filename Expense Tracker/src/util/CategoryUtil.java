package util;
import Enum.Category;
public class CategoryUtil {
    public static String getCombinedCategory(String category, String subCategory){
        for(Category cat: Category.values()){
            if(cat.getDisplayName().equalsIgnoreCase(category)){
                if(cat.getSubCategories().length > 0 && subCategory != null){
                    for(String sub: cat.getSubCategories()){
                        if(sub.equalsIgnoreCase(subCategory)){
                            return cat.getDisplayName()+"~"+sub;
                        }
                    }
                }
                return category;
            }
        }
        return "";
    }

}
