package none.cgutils.license;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import none.cgutils.license.api.LicenseApiClient;
import none.cgutils.license.domain.License;
import none.cgutils.license.domain.Rules;
import none.cgutils.license.gui.LicenseQueryDialog;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LicenseAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        List<License> licenses = LicenseApiClient.getAllLicenses();
        licenses.add(0, new License("Select License", "select_license", null, "Select a license", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

        Rules rules = LicenseApiClient.getAllRules();

        Project project = event.getData(PlatformDataKeys.PROJECT);

        LicenseQueryDialog licenseQueryDialog = new LicenseQueryDialog(project, licenses, rules);
        licenseQueryDialog.pack();
        licenseQueryDialog.setVisible(true);
    }
}
