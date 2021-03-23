package com.lucifiere.coderplus.model.meta;

import com.lucifiere.coderplus.model.Model;

import java.util.List;

/**
 * 复合model
 *
 * @author created by XD.Wang
 * Date 2020/12/17.
 */
public interface CompoundModel {

    /**
     * 获取成员model
     *
     * @return 成员model
     */
    List<? extends Model> getMemberModels();

}
