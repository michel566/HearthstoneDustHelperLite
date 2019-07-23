package br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model;

public class SpinnerClassItem {

    private String className;
    private int classIcon;

    public SpinnerClassItem(String className, int classIcon) {
        this.className = className;
        this.classIcon = classIcon;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getClassIcon() {
        return classIcon;
    }

    public void setClassIcon(int classIcon) {
        this.classIcon = classIcon;
    }

}
