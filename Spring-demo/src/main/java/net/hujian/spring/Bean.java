package net.hujian.spring;

/**
 * Created by hujian on 16-9-5.
 * the bean.
 */
public class Bean {
    private String name=null;
    private int old=0;

    /**
     * set name
     * @param nm
     */
    public void setName(String nm){
        this.name=nm;
    }

    /**
     * set the old
     * @param o
     */
    public  void setOld(int o){
        this.old=o;
    }

    /**
     * to string
     */
    public String toString(){
        return "[I am "+this.name+" "+this.old+" old]";
    }
}
