package none.cgutils.license.gui;

import none.cgutils.license.domain.License;
import none.cgutils.license.domain.Rules;
import none.cgutils.license.domain.TagLabelDecription;
import org.jetbrains.annotations.NotNull;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

public class UIHelper {

    public ListCellRenderer<License> getLicenseNameRenderer() {
        return new ListCellRenderer<License>() {
            protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

            @Override
            public Component getListCellRendererComponent(JList<? extends License> list, License value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                renderer.setText(value.getTitle());
                return renderer;
            }
        };
    }

    @NotNull
    private JCheckBox getCheckBox(TagLabelDecription tagLabelDecription) {
        JCheckBox checkbox = new JCheckBox();
        checkbox.setText(tagLabelDecription.getLabel());
        checkbox.setToolTipText(tagLabelDecription.getDescription());
        checkbox.setActionCommand(tagLabelDecription.getTag());
        checkbox.setEnabled(false);
        return checkbox;
    }

    public List<JCheckBox> getPermissionsCheckBoxes(Rules rules) {
        List<JCheckBox> permissionsCheckBoxes = new ArrayList<>();
        for (TagLabelDecription tagLabelDecription : rules.getPermissions()) {
            JCheckBox checkbox = getCheckBox(tagLabelDecription);
            permissionsCheckBoxes.add(checkbox);
        }
        return permissionsCheckBoxes;
    }

    public List<JCheckBox> getLimitationsCheckBoxes(Rules rules) {
        List<JCheckBox> limitationsCheckBoxes = new ArrayList<>();
        for (TagLabelDecription tagLabelDecription : rules.getLimitations()) {
            JCheckBox checkbox = getCheckBox(tagLabelDecription);
            limitationsCheckBoxes.add(checkbox);
        }
        return limitationsCheckBoxes;
    }

    public List<JCheckBox> getConditionsCheckBoxes(Rules rules) {
        List<JCheckBox> conditionsCheckBoxes = new ArrayList<>();
        for (TagLabelDecription tagLabelDecription : rules.getConditions()) {
            JCheckBox checkbox = getCheckBox(tagLabelDecription);
            conditionsCheckBoxes.add(checkbox);
        }
        return conditionsCheckBoxes;
    }

}
