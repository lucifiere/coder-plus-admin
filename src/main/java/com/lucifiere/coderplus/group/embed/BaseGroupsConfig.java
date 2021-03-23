package com.lucifiere.coderplus.group.embed;

import com.lucifiere.coderplus.group.spec.Group;
import com.lucifiere.coderplus.group.spec.GroupSpec;
import com.lucifiere.coderplus.group.spec.Groups;
import com.lucifiere.coderplus.templates.embed.EmbedTemplates;

/**
 * @author created by wuhuilin
 * Date 2020/9/14.
 */
@Groups
public class BaseGroupsConfig {

    public static final String STANDARD_GROUP = "standard";

    @Group("standard")
    public GroupSpec standardGroup() {
        GroupSpec g = new GroupSpec();
        g.addTemplate(EmbedTemplates.EXAMPLE);
        g.addTemplate(EmbedTemplates.MODEL);
        return g;
    }
}
