package net.hujian.mybatis;

/**
 * Created by hujian on 16-9-5.
 */
public class Info {
    private int id=0;

    /**
     *
     * @param i
     */
    public void setId(int i){
        this.id=i;
    }
    private String name=null;

    /**
     *
     * @param nm
     */
    public void setName(String nm){
        this.name=nm;
    }
    private int age=0;

    /**
     *
     * @param a
     */
    public void setAge(int a){
        this.age=a;
    }
    private String school=null;

    /**
     *
     * @param edu
     */
    public  void setSchool(String edu){
        this.school=edu;
    }
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return "I am "+this.name+",and i am "+this.age+" years old,my E-mail:"+
                this.name+"@"+this.school+".edu.cn]";
    }
}
