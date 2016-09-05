package net.hujian.spring;

import org.springframework.beans.factory.annotation.Configurable;

/**
 * Created by hujian on 16-9-5.
 */
@Configurable
public class JBean {
    @org.springframework.context.annotation.Bean(name="JBs")
    public Bean getBean(){
        return new Bean();
    }
}
