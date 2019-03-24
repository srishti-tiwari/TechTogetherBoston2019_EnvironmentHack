package techtogether.io.myapplication;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {

    String categoryName;
    List<Action> actionList;

    public Category(String categoryName, List<Action> actionList) {
        this.categoryName = categoryName;
        this.actionList = actionList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Action> getActionList() {
        return actionList;
    }

    public void setActionList(List<Action> actionList) {
        this.actionList = actionList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                ", actionList=" + actionList +
                '}';
    }
}
