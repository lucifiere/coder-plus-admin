package com.lucifiere.coderplus.model;

import com.lucifiere.coderplus.model.meta.Field;
import com.google.common.base.Joiner;
import com.lucifiere.coderplus.model.enums.BaseBuiltInAttr;
import com.lucifiere.coderplus.model.enums.ModelBuiltInAttr;
import com.lucifiere.coderplus.utils.CodeStyle;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 存放ThriftDto模型数据
 *
 * @author XD.Wang
 * Date 2020-7-25.
 */
public class ThriftStructModel extends ThriftModel {

    private final Map<String, Field> fields = new HashMap<>();

    public void addTableFiled(Field field) {
        fields.put(field.getName(), field);
    }

    @Override
    public String toString() {
        return Joiner.on("\n").join(fields.values().stream().map(Field::toString).collect(Collectors.toList()));
    }

    @Override
    public Map<String, Object> toAttrMap() {
        // extract table attrs
        Map<String, Object> m = super.toAttrMap();
        addBuiltInAttr(ModelBuiltInAttr.MODEL.key(), CodeStyle.ofUlCode(keyword).toStyle(CodeStyle.NamedStyle.CAMEL).toString())
                .addBuiltInAttr(ModelBuiltInAttr.MODEL_CAPTAl_FIRST_NAME.key(), CodeStyle.ofUlCode(keyword).toStyle(CodeStyle.NamedStyle.CAMEL).toStyle(CodeStyle.NamedStyle.CAP_FIRST).toString())
                .addBuiltInAttr(ModelBuiltInAttr.MODEL_CAMEL_NAME.key(), CodeStyle.ofUlCode(keyword).toStyle(CodeStyle.NamedStyle.CAMEL).toString())
                .addBuiltInAttr(ModelBuiltInAttr.MODEL_UNDERLINE_NAME.key(), CodeStyle.ofUlCode(keyword).toString())
                .addBuiltInAttr(BaseBuiltInAttr.BASE_PACKAGE.key(), CodeStyle.ofUlCode(nameSpace).toString())
                // extract table filed attrs
                .addBuiltInAttr(ModelBuiltInAttr.FIELD.key(), Objects.requireNonNull(fields.values()).stream().map(this::createFiledMap).filter(Objects::nonNull).collect(Collectors.toList()));
        // extract single primary key
        return m;
    }

}
