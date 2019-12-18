package none.cgutils.license.domain;

import java.util.ArrayList;
import java.util.List;

public class Rules {

    List<TagLabelDecription> permissions = new ArrayList<>();
    List<TagLabelDecription> limitations = new ArrayList<>();
    List<TagLabelDecription> conditions = new ArrayList<>();

    public List<TagLabelDecription> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<TagLabelDecription> permissions) {
        this.permissions = permissions;
    }

    public List<TagLabelDecription> getLimitations() {
        return limitations;
    }

    public void setLimitations(List<TagLabelDecription> limitations) {
        this.limitations = limitations;
    }

    public List<TagLabelDecription> getConditions() {
        return conditions;
    }

    public void setConditions(List<TagLabelDecription> conditions) {
        this.conditions = conditions;
    }

    public void addPermission(TagLabelDecription tagLabelDecription) {
        this.permissions.add(tagLabelDecription);
    }

    public void addLimitation(TagLabelDecription tagLabelDecription) {
        this.limitations.add(tagLabelDecription);
    }

    public void addCondition(TagLabelDecription tagLabelDecription) {
        this.conditions.add(tagLabelDecription);
    }

    @Override
    public String toString() {
        return "Rules{" +
                "permissions=" + permissions +
                ", limitations=" + limitations +
                ", conditions=" + conditions +
                '}';
    }
}
