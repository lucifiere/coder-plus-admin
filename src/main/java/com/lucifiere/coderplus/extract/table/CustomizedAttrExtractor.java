package com.lucifiere.coderplus.extract.table;

import com.lucifiere.coderplus.container.ManagedBean;
import com.lucifiere.coderplus.extract.AbstractExtractor;
import com.lucifiere.coderplus.model.Model;
import com.lucifiere.coderplus.model.loader.GlobalConfigAttrLoader;
import com.lucifiere.coderplus.model.loader.ModelAttrLoader;
import com.lucifiere.coderplus.model.loader.PropertiesAttrLoader;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 自定义数据提取器
 *
 * @author XD.Wang
 * Date 2020-7-25.
 */
@ManagedBean
public class CustomizedAttrExtractor extends AbstractExtractor {

    private final List<ModelAttrLoader> modelAttrLoaderList = Lists.newArrayList(
            GlobalConfigAttrLoader.getLoader()
    );

    @Override
    public Model extract() {
        Model m = new Model();
        modelAttrLoaderList.forEach(loader -> {
            loader.loadTo(m);
        });
        return m;
    }

}
