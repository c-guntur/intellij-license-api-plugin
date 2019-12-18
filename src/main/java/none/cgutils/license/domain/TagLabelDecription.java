package none.cgutils.license.domain;

public class TagLabelDecription {

    private String tag;
    private String label;
    private String description;

    public TagLabelDecription() {
    }

    public TagLabelDecription(String tag, String label, String description) {
        this.tag = tag;
        this.label = label;
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TagLabelDecription{" +
                "tag='" + tag + '\'' +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
