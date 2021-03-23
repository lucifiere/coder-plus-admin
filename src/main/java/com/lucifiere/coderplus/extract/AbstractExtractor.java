package com.lucifiere.coderplus.extract;

import com.lucifiere.coderplus.container.GlobalContext;
import com.lucifiere.coderplus.container.GlobalContextAware;
import com.lucifiere.coderplus.container.ManagedBean;

/**
 * 预置数据提取器
 *
 * @author XD.Wang
 * Date 2020-7-25.
 */
public abstract class AbstractExtractor implements Extractor, GlobalContextAware {

    protected GlobalContext globalContext;

    @Override
    public void setGlobalContext(GlobalContext globalContext) {
        this.globalContext = globalContext;
    }

}
