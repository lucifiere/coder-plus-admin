package com.lucifiere.coderplus.exporter;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.google.common.base.Preconditions;
import com.lucifiere.coderplus.common.FileSetting;
import com.lucifiere.coderplus.common.FileType;
import com.lucifiere.coderplus.container.ManagedBean;
import com.lucifiere.coderplus.io.FileAccessor;
import com.lucifiere.coderplus.render.View;
import com.lucifiere.coderplus.render.views.SourceCodeView;
import com.lucifiere.coderplus.utils.CodeStyle;
import com.lucifiere.coderplus.utils.ExceptionUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 代码输出工具
 *
 * @author XD.Wang
 * Date 2020-8-2.
 */
@ManagedBean
public class CodeExporter extends AbstractExporter {

    @Override
    public void export(List<View> views) {
        checkViewType(views);
        this.views = views;
        String outPath = getOutputPath();
        FileUtil.del(getOutputPath());
        this.views.stream().map(view -> (SourceCodeView) view).forEach(ExceptionUtils.ioConsumer(view -> {
            if (StrUtil.isNotBlank(view.getFileSetting().getFileDir())) {
                Path path = Paths.get(getOutputPath() + view.getFileSetting().getFileDir());
                if (!Files.exists(path)) {
                    FileUtil.mkdir(FileUtil.newFile(getOutputPath() + view.getFileSetting().getFileDir()));
                }
            }
            FileAccessor.createFile(view.getContent(), outPath, createFilePath(view));
        }));
        ZipUtil.zip(outPath, StandardCharsets.UTF_8);
    }

    private void checkViewType(List<View> views) {
        Preconditions.checkNotNull(views);
        Preconditions.checkArgument(views.stream().allMatch(v -> v instanceof SourceCodeView));
    }

    private String createFilePath(SourceCodeView view) {
        FileSetting fileSetting = view.getFileSetting();
        if (StrUtil.isNotBlank(fileSetting.getFileName())) {
            return fileSetting.getFileName();
        }
        String filePath = Optional.ofNullable(view.getFileSetting().getFileDir()).orElse("");
        if (Objects.equals(fileSetting.getFileType(), FileType.MYBATIS_XML)) {
            String fileName = CodeStyle.ofUlCode(view.getName()).toStyle(CodeStyle.NamedStyle.CAMEL).toStyle(CodeStyle.NamedStyle.CAP_FIRST).toString();
            filePath += fileSetting.getPrefix() + fileName + fileSetting.getSuffix() + fileSetting.getFileType().getExt();
        } else if (Objects.equals(fileSetting.getFileType(), FileType.JAVA)) {
            filePath += fileSetting.getPrefix() + getJavaClassName(view.getContent()) + fileSetting.getSuffix() + FileType.JAVA.getExt();
        }
        return filePath;
    }

    private String getJavaClassName(String codeContent) {
        int keywordIdx = Stream.of(codeContent.indexOf("class"), codeContent.indexOf("interface"), codeContent.indexOf("enum"))
                .filter(it -> it > 0).mapToInt(it -> it).min().orElse(-1);
        int nextBracketIdx = Stream.of(codeContent.indexOf("{", keywordIdx), codeContent.indexOf("implements", keywordIdx), codeContent.indexOf("extends", keywordIdx))
                .filter(it -> it > 0).mapToInt(it -> it).min().orElse(-1);
        if (keywordIdx == -1 || nextBracketIdx == -1) {
            throw new RuntimeException("not a standard java file!");
        }
        return codeContent.substring(codeContent.indexOf(" ", keywordIdx), nextBracketIdx).trim();
    }

}
