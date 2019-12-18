package none.cgutils.license.gui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.openapi.wm.ex.WindowManagerEx;
import com.intellij.openapi.wm.impl.IdeFrameImpl;
import com.intellij.ui.components.JBScrollPane;
import none.cgutils.license.domain.License;
import none.cgutils.license.domain.Rules;
import org.jetbrains.annotations.NotNull;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class LicenseQueryDialog extends JDialog {

    List<License> licenses;
    Rules rules;

    private UIHelper uiHelper;

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private ComboBox<License> licenseList;
    private JTextArea descriptionTextArea;
    private List<JCheckBox> permissions;
    private List<JCheckBox> limitations;
    private List<JCheckBox> conditions;

    public LicenseQueryDialog(Project project, List<License> licenses, Rules rules) {

        this.licenses = licenses;
        this.rules = rules;

        init();

        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(getParentWindow(project));
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        licenseList.addItemListener(e -> {
            License license = (License) e.getItem();
            descriptionTextArea.setText(license.getDescription());
            checkPermissions(license.getPermissions());
            checkLimitations(license.getLimitations());
            checkConditions(license.getConditions());
        });
    }

    public static void main(String[] args) {
        LicenseQueryDialog dialog = new LicenseQueryDialog(null, null, null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void checkPermissions(List<String> licensePermissions) {
        permissions.forEach(jCheckBox -> {
            jCheckBox.setSelected(licensePermissions.contains(jCheckBox.getActionCommand()));
        });
    }

    private void checkLimitations(List<String> licenseLimitations) {
        limitations.forEach(jCheckBox -> {
            jCheckBox.setSelected(licenseLimitations.contains(jCheckBox.getActionCommand()));
        });
    }

    private void checkConditions(List<String> licenseConditions) {
        conditions.forEach(jCheckBox -> {
            jCheckBox.setSelected(licenseConditions.contains(jCheckBox.getActionCommand()));
        });
    }

    private void init() {
        uiHelper = new UIHelper();

        JPanel bottomPanel = createBottomPanel();

        JPanel licensePanel = createLicensePanel();

        JPanel descriptionPanel = createDescriptionPanel();

        JPanel permissionsPanel = createPermissionsPanel();
        JPanel limitationsPanel = createLimitationsPanel();
        JPanel conditionsPanel = createConditionsPanel();

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5, 1));
        centerPanel.add(licensePanel);
        centerPanel.add(descriptionPanel);
        centerPanel.add(permissionsPanel);
        centerPanel.add(limitationsPanel);
        centerPanel.add(conditionsPanel);

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);
    }

    @NotNull
    private JPanel createBottomPanel() {
        buttonOK = new JButton("Ok");
        buttonCancel = new JButton("Cancel");
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(0, 0, FlowLayout.RIGHT));
        bottomPanel.add(buttonOK);
        bottomPanel.add(buttonCancel);
        return bottomPanel;
    }

    private JPanel createLicensePanel() {
        JLabel licenseListLabel = new JLabel("Select License:");
        licenseListLabel.setLabelFor(licenseList);
        licenseList = new ComboBox<>(licenses.toArray(new License[0]));
        licenseList.setRenderer(uiHelper.getLicenseNameRenderer());
        JPanel licensePanel = new JPanel();
        licensePanel.setLayout(new FlowLayout());
        licensePanel.add(licenseListLabel);
        licensePanel.add(licenseList);
        return licensePanel;
    }

    private JPanel createDescriptionPanel() {
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setLabelFor(descriptionTextArea);

        descriptionTextArea = new JTextArea();
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setEnabled(false);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setForeground(descriptionLabel.getForeground());
        Font f = descriptionTextArea.getFont();
        Font f2 = new Font(f.getFontName(), f.getStyle(), f.getSize() - 2);
        descriptionTextArea.setFont(f2);

        JBScrollPane descriptionTextAreaScroll = new JBScrollPane(descriptionTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel descriptionPanel = new JPanel();

        descriptionPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        descriptionPanel.add(descriptionLabel, c);
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 50;      //make this component tall
        c.weightx = 0.5;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        descriptionPanel.add(descriptionTextAreaScroll, c);
        return descriptionPanel;
    }

    private JPanel createPermissionsPanel() {
        permissions = uiHelper.getPermissionsCheckBoxes(rules);

        JLabel permissionsLabel = new JLabel("Permissions");

        return createCheckBoxPanel(permissions, permissionsLabel);
    }

    private JPanel createLimitationsPanel() {
        limitations = uiHelper.getLimitationsCheckBoxes(rules);

        JLabel limitationsLabel = new JLabel("Limitations");

        return createCheckBoxPanel(limitations, limitationsLabel);
    }

    private JPanel createConditionsPanel() {
        conditions = uiHelper.getConditionsCheckBoxes(rules);

        JLabel conditionsLabel = new JLabel("Conditions");

        return createCheckBoxPanel(conditions, conditionsLabel);
    }

    @NotNull
    private JPanel createCheckBoxPanel(List<JCheckBox> checkboxes, JLabel titleLabel) {
        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new GridLayout(3, 2));
        checkboxes.forEach(jCheckBox -> checkboxPanel.add(jCheckBox));

        JPanel checkboxWithTitlePanel = new JPanel();
        checkboxWithTitlePanel.setLayout(new BorderLayout());
        checkboxWithTitlePanel.add(titleLabel, BorderLayout.NORTH);
        checkboxWithTitlePanel.add(checkboxPanel, BorderLayout.CENTER);
        return checkboxWithTitlePanel;
    }

    private Window getParentWindow(Project project) {
        WindowManagerEx windowManager = (WindowManagerEx) WindowManager.getInstance();

        Window window = windowManager.suggestParentWindow(project);
        if (window == null) {
            Window focusedWindow = windowManager.getMostRecentFocusedWindow();
            if (focusedWindow instanceof IdeFrameImpl) {
                window = focusedWindow;
            }
        }
        return window;
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
