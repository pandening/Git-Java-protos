package net.hujian.tc;

/**
 * Created by hujian on 16-9-5.
 */
public class Class {
    private int id=0;
    private String name=null;
    private Teacher teacher=null;
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
    public void setTeacher(Teacher t){
        this.teacher=t;
    }
    public Teacher getTeacher(){
        return this.teacher;
    }
    @Override
    public String toString(){
        return "[Class_"+this.name+"@"+this.id+"]teacher is:"+this.teacher.getName();
    }
}
