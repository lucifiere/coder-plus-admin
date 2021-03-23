package com.lucifiere.coderplus.exporter;

import com.lucifiere.coderplus.render.View;

import java.util.List;

/**
 * 输出工具，输出结果到端
 *
 * @author created by XD.Wang
 * Date 2020/7/12.
 */
public interface Exporter {

    /**
     * 导出代码
     *
     * @param views 视图
     */
    void export(List<View> views);

    /**
     * 获取路径
     *
     * @return 路径
     */
    String getOutputPath();

    /**
     * 获取视图
     *
     * @return 视图
     */
    List<View> getViews();

}
