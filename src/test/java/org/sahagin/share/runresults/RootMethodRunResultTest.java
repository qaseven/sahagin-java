package org.sahagin.share.runresults;

import java.io.File;
import java.util.Map;

import org.junit.Test;
import org.sahagin.TestBase;
import org.sahagin.share.yaml.YamlConvertException;
import org.sahagin.share.yaml.YamlUtils;

public class RootMethodRunResultTest extends TestBase {

    // convert YAML -> rootMethodRunResult -> YAML, then compare 2 YAML
    @Test
    public void yamlConversion() throws YamlConvertException {
        File fromYamlFile = new File(testResourceDir("yamlConversion"), "rootMethodRunResult");
        Map<String, Object> fromYamlObj = YamlUtils.load(fromYamlFile);
        RootMethodRunResult result = new RootMethodRunResult();
        result.fromYamlObject(fromYamlObj);
        Map<String, Object> toYamlObj = result.toYamlObject();
        assertYamlEquals(fromYamlObj, toYamlObj);
    }
}
