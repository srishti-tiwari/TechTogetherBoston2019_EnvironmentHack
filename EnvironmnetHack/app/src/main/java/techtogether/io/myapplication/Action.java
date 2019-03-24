package techtogether.io.myapplication;

import java.io.Serializable;

public class Action implements Serializable {
    String categoryName;
    String actionName;
    int ecoscore;

    public Action(String categoryName, String actionName, int ecoscore) {
        this.categoryName = categoryName;
        this.actionName = actionName;
        this.ecoscore = ecoscore;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getEcoscore() {
        return ecoscore;
    }

    public void setEcoscore(int ecoscore) {
        this.ecoscore = ecoscore;
    }

    @Override
    public String toString() {
        return "Action{" +
                "categoryName='" + categoryName + '\'' +
                ", actionName='" + actionName + '\'' +
                ", ecoscore=" + ecoscore +
                '}';
    }


}
