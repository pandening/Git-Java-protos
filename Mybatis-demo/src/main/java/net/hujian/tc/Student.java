package net.hujian.tc;

/**
 * Created by hujian on 16-9-5.
 */
public class Student {
    private int id=0;
    private String name=null;
    public void setId(int i){
        this.id=i;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String nm){
        this.name=nm;
    }
    public String getName(){
        return this.name;
    }
    @Override
    public String toString(){
        return "["+this.id+"@"+this.name+"]";
    }
}
