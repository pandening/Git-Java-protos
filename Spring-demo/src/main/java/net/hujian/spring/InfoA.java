package net.hujian.spring;

/**
 * Created by hujian on 16-9-5.
 */
public class InfoA {
    private String name=null;
    public void setName(String nm){
        this.name=nm;
    }
    private String school=null;
    public void setSchool(String edu){
        this.school=edu;
    }
    @Override
    public String toString(){
        return "["+name+"@"+school+".edu.cn]";
    }
}
