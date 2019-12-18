package none.cgutils.license.api;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import none.cgutils.license.domain.License;
import none.cgutils.license.domain.Rules;
import none.cgutils.license.domain.TagLabelDecription;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LicenseApiClient {

    private static final String RULES_URL = "https://licenseapi.herokuapp.com/rules";
    private static final String RULES_FILE = "rules.json";

    private static final String ALL_LICENSES_URL = "https://licenseapi.herokuapp.com/licenses";
    private static final String ALL_LICENSES_FILE = "licenses.json";

    private static final String TITLE = "title";
    private static final String ID = "id";
    private static final String NICKNAME = "nickname";
    private static final String DESCRIPTION = "description";
    private static final String PERMISSIONS = "permissions";
    private static final String LIMITATIONS = "limitations";
    private static final String CONDITIONS = "conditions";
    private static final String TAG = "tag";
    private static final String LABEL = "label";

    @Nullable
    private static String getFileContent(boolean fetchFromWeb, String url, String fileName) {
        String json = null;
        if (fetchFromWeb) {
            try {
                json = Utilities.readUrl(new URL(url));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            ClassLoader classLoader = new LicenseApiClient().getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            try {
                json = new String(Files.readAllBytes(file.toPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return json;
    }

    private static String getRulesJson(boolean fetchFromWeb) {
        return getFileContent(fetchFromWeb, RULES_URL, RULES_FILE);
    }

    public static final Rules getAllRules() {
        return getAllRules(true);
    }

    public static final Rules getAllRules(boolean fetchFromWeb) {
        String json = null;
        Rules rules = new Rules();
        DocumentContext rulesJson = JsonPath.parse(getRulesJson(fetchFromWeb));
        List<Map<String, String>> eachRuleList = rulesJson.read("$.rules.permissions");
        rules.setPermissions(extractTagLabelDescription(eachRuleList));

        eachRuleList = rulesJson.read("$.rules.limitations");
        rules.setLimitations(extractTagLabelDescription(eachRuleList));

        eachRuleList = rulesJson.read("$.rules.conditions");
        rules.setConditions(extractTagLabelDescription(eachRuleList));

        return rules;
    }

    private static List<TagLabelDecription> extractTagLabelDescription(List<Map<String, String>> each) {
        List<TagLabelDecription> tagLabelDecriptions = new ArrayList<>();
        for (int i = 0; i < each.size(); i++) {
            Map<String, String> eachTLD = each.get(i);
            TagLabelDecription tagLabelDecription = new TagLabelDecription();
            tagLabelDecription.setTag(eachTLD.get(TAG));
            tagLabelDecription.setLabel(eachTLD.get(LABEL));
            tagLabelDecription.setDescription(eachTLD.get(DESCRIPTION));
            tagLabelDecriptions.add(tagLabelDecription);
        }
        return tagLabelDecriptions;
    }

    private static String getLicensesJson(boolean fetchFromWeb) {
        return getFileContent(fetchFromWeb, ALL_LICENSES_URL, ALL_LICENSES_FILE);
    }

    public static final List<License> getAllLicenses() {
        return getAllLicenses(true);
    }

    public static final List<License> getAllLicenses(boolean fetchFromWeb) {
        List<License> licenses = new ArrayList<>();
        try {
            String json = getLicensesJson(fetchFromWeb);
            List<Map<String, Object>> licensesList = JsonPath.parse(json).read("$.licenses");
            for (int i = 0; i < licensesList.size(); i++) {
                Map<String, Object> licenseObject = licensesList.get(i);
                License license = new License();

                if (licenseObject.get(TITLE) != null) {
                    license.setTitle(licenseObject.get(TITLE).toString());
                }

                if (licenseObject.get(ID) != null) {
                    license.setId(licenseObject.get(ID).toString());
                }

                if (licenseObject.get(NICKNAME) != null) {
                    license.setNickname(licenseObject.get(NICKNAME).toString());
                }

                if (licenseObject.get(DESCRIPTION) != null) {
                    license.setDescription(licenseObject.get(DESCRIPTION).toString());
                }

                if (licenseObject.get(PERMISSIONS) != null) {
                    license.setPermissions((List<String>) licenseObject.get(PERMISSIONS));
                }

                if (licenseObject.get(LIMITATIONS) != null) {
                    license.setLimitations((List<String>) licenseObject.get(LIMITATIONS));
                }

                if (licenseObject.get(CONDITIONS) != null) {
                    license.setConditions((List<String>) licenseObject.get(CONDITIONS));
                }

                licenses.add(license);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return licenses;
    }

    public static void main(String[] args) {
//        getAllRules();
//        System.out.println(getAllRules());
//        System.out.println(getAllLicenses());
    }

}
