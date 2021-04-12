package com.lucifiere.coderplus.model.loader;

import com.lucifiere.coderplus.model.Model;

/**
 * 模型属性加载
 *
 * @author created by XD.Wang
 * Date 2020/9/4.
 */
public interface ModelAttrLoader {

    /**
     * 为model加载额外属性
     *
     * @param model 被加载的model
     */
    void loadTo(Model model);

}
