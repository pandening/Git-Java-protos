package net.hujian.spring;

/**
 * Created by hujian on 16-9-5.
 */
public class OutHelper {
    private Output output=null;
    public void setOutput(Output out){
        this.output=out;
    }
    public void o(){
        this.output.out();
    }
}
