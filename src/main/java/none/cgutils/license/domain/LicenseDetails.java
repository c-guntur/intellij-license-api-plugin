package none.cgutils.license.domain;

import java.util.Objects;

public class LicenseDetails {

    private String spdxId;
    private boolean featured;
    private boolean hidden;
    private String how;
    private String note;
    private String text;

    public LicenseDetails() {
    }

    public LicenseDetails(String spdxId, boolean featured, boolean hidden, String how, String note, String text) {
        this.spdxId = spdxId;
        this.featured = featured;
        this.hidden = hidden;
        this.how = how;
        this.note = note;
        this.text = text;
    }

    public LicenseDetails spdxId(final String spdxId) {
        this.spdxId = spdxId;
        return this;
    }

    public LicenseDetails featured(final boolean featured) {
        this.featured = featured;
        return this;
    }

    public LicenseDetails hidden(final boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    public LicenseDetails how(final String how) {
        this.how = how;
        return this;
    }

    public LicenseDetails note(final String note) {
        this.note = note;
        return this;
    }

    public LicenseDetails text(final String text) {
        this.text = text;
        return this;
    }

    public String getSpdxId() {
        return spdxId;
    }

    public void setSpdxId(String spdxId) {
        this.spdxId = spdxId;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getHow() {
        return how;
    }

    public void setHow(String how) {
        this.how = how;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LicenseDetails that = (LicenseDetails) o;
        return featured == that.featured &&
                hidden == that.hidden &&
                Objects.equals(spdxId, that.spdxId) &&
                Objects.equals(how, that.how) &&
                Objects.equals(note, that.note) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spdxId, featured, hidden, how, note, text);
    }

    @Override
    public String toString() {
        return "LicenseDetails{" +
                "spdxId='" + spdxId + '\'' +
                ", featured=" + featured +
                ", hidden=" + hidden +
                ", how='" + how + '\'' +
                ", note='" + note + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
