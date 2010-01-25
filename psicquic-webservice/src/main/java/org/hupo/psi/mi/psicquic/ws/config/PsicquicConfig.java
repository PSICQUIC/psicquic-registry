/**
 * Copyright 2008 The European Bioinformatics Institute, and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hupo.psi.mi.psicquic.ws.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Place-holder for the configuration. Initialized by Spring.
 *
 * @author Bruno Aranda (baranda@ebi.ac.uk)
 * @version $Id$
 */
public class PsicquicConfig {

    private String groupId;
    private String artifactId;
    private String version;
    private String specVersion;
    private String indexDirectory;
    private String propertiesAsStrings;

    public PsicquicConfig() {
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIndexDirectory() {
        return indexDirectory;
    }

    public void setIndexDirectory(String indexDirectory) {
        this.indexDirectory = indexDirectory;
    }

    public Map<String,String> getProperties() {
        String propsAsString = getPropertiesAsStrings();

        if (propsAsString == null) return Collections.EMPTY_MAP;

        Map<String,String> propMap = new HashMap<String, String>();

        String[] props = propsAsString.split(",");

        for (String prop : props) {
            String[] propTokens = prop.trim().split("=");

            propMap.put(propTokens[0], propTokens[1]);
        }

        propMap.put("psicquic.spec.version", getSpecVersion());
        propMap.put("psicquic.implementation.name", "Reference Implementation");
        propMap.put("psicquic.implementation.version", getVersion());

        return propMap;
    }

    public String getPropertiesAsStrings() {
        return propertiesAsStrings;
    }

    public void setPropertiesAsStrings(String propertiesAsStrings) {
        this.propertiesAsStrings = propertiesAsStrings;
    }

    public String getSpecVersion() {
        return specVersion;
    }

    public void setSpecVersion(String specVersion) {
        this.specVersion = specVersion;
    }
}
