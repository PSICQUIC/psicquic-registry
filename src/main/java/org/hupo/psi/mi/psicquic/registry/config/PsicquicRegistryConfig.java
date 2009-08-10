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
package org.hupo.psi.mi.psicquic.registry.config;

import org.hupo.psi.mi.psicquic.registry.ServiceType;

import java.util.List;

/**
 * Place-holder for the configuration. Initialized by Spring.
 *
 * @author Bruno Aranda (baranda@ebi.ac.uk)
 * @version $Id$
 */
public class PsicquicRegistryConfig {

    private String version;
    private List<ServiceType> registeredServices;

    public PsicquicRegistryConfig() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<ServiceType> getRegisteredServices() {
        return registeredServices;
    }

    public void setRegisteredServices(List<ServiceType> registeredServices) {
        this.registeredServices = registeredServices;
    }
}
