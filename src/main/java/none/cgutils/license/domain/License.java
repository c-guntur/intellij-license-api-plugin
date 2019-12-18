package none.cgutils.license.domain;

import java.util.ArrayList;
import java.util.List;

public class License {

    private String title;
    private String id;
    private String nickname;
    private String description;
    private List<String> permissions = new ArrayList<>();
    private List<String> conditions = new ArrayList<>();
    private List<String> limitations = new ArrayList<>();
    private LicenseDetails licenseDetails = null;

    public License() {
    }

    public License(String title,
                   String id,
                   String nickname,
                   String description,
                   List<String> permissions,
                   List<String> conditions,
                   List<String> limitations) {
        this.title = title;
        this.id = id;
        this.nickname = nickname;
        this.description = description;
        this.permissions = permissions;
        this.conditions = conditions;
        this.limitations = limitations;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<String> getConditions() {
        return conditions;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }

    public List<String> getLimitations() {
        return limitations;
    }

    public void setLimitations(List<String> limitations) {
        this.limitations = limitations;
    }

    public LicenseDetails getLicenseDetails() {
        return licenseDetails;
    }

    public void setLicenseDetails(LicenseDetails licenseDetails) {
        this.licenseDetails = licenseDetails;
    }

    public String getSpdxId() {
        return licenseDetails.getSpdxId();
    }

    public void setSpdxId(String spdxId) {
        licenseDetails.setSpdxId(spdxId);
    }

    public boolean isFeatured() {
        return licenseDetails.isFeatured();
    }

    public void setFeatured(boolean featured) {
        licenseDetails.setFeatured(featured);
    }

    public boolean isHidden() {
        return licenseDetails.isHidden();
    }

    public void setHidden(boolean hidden) {
        licenseDetails.setHidden(hidden);
    }

    public String getHow() {
        return licenseDetails.getHow();
    }

    public void setHow(String how) {
        licenseDetails.setHow(how);
    }

    public String getNote() {
        return licenseDetails.getNote();
    }

    public void setNote(String note) {
        licenseDetails.setNote(note);
    }

    public String getText() {
        return licenseDetails.getText();
    }

    public void setText(String text) {
        licenseDetails.setText(text);
    }

    @Override
    public String toString() {
        return "License{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", description='" + description + '\'' +
                ", permissions=" + permissions +
                ", conditions=" + conditions +
                ", limitations=" + limitations +
                ", licenseDetails=" + licenseDetails +
                '}';
    }
}
