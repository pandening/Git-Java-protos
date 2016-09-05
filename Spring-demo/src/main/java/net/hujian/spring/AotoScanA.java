package net.hujian.spring;

import org.springframework.stereotype.Component;

/**
 * Created by hujian on 16-9-5.
 */
@Component("AutoA")
public class AotoScanA {
    @Override
    public String toString(){
        return "[+"+this.getClass().getName()+"]";
    }
}
