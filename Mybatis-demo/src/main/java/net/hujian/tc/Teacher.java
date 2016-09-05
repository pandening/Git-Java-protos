package net.hujian.tc;

/**
 * Created by hujian on 16-9-5.
 */
public class Teacher {
    private int id=0;
    private String name=null;

    /**
     *
     * @param i
     */
    public void setId(int i){
        this.id=i;
    }

    /**
     *
     * @return
     */
    public int getId(){
        return this.id;
    }

    /**
     *
     * @param nm
     */
    public void setName(String nm){
        this.name=nm;
    }
    /**
     *
     * @return
     */
    public String getName(){
        return this.name;
    }
    @Override
    public  String toString(){
        return "[Teacher->"+this.name+"@"+this.id+"]";
    }
}
