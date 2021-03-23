package com.lucifiere.coderplus.utils;

import com.lucifiere.coderplus.container.GlobalContext;
import com.lucifiere.coderplus.container.GlobalContextAware;
import com.lucifiere.coderplus.container.ManagedBean;

/**
 * @author created by XD.Wang
 * Date 2020/9/7.
 */
@ManagedBean
final public class GlobalContextHolder implements GlobalContextAware {

    public static GlobalContext globalContext;

    @Override
    public void setGlobalContext(GlobalContext c) {
        globalContext = c;
    }

}
