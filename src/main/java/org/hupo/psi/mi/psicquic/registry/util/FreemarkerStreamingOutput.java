/**
 * Copyright 2009 The European Bioinformatics Institute, and others.
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
package org.hupo.psi.mi.psicquic.registry.util;

import org.hupo.psi.mi.psicquic.PsicquicService;
import org.hupo.psi.mi.psicquic.QueryResponse;
import org.hupo.psi.mi.psicquic.RequestInfo;
import org.hupo.psi.mi.psicquic.freemarker.method.TermName;
import org.hupo.psi.mi.psicquic.ols.client.SelfDiscoveringOntologyTree;
import org.hupo.psi.mi.psicquic.registry.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Bruno Aranda (baranda@ebi.ac.uk)
 * @version $Id$
 */

public class FreemarkerStreamingOutput implements StreamingOutput {

    private Registry registry;
    private Configuration configuration;
    private SelfDiscoveringOntologyTree miOntologyTree; 
    



    
    public FreemarkerStreamingOutput(Registry registry, SelfDiscoveringOntologyTree miOntologyTree,Configuration config) {
        this.registry = registry;
        this.configuration = config;
        this.miOntologyTree = miOntologyTree;
    }

    public void write(OutputStream outputStream) throws IOException, WebApplicationException {
        final Template template = configuration.getTemplate("main.ftl");
        
        Writer writer = new OutputStreamWriter(outputStream);
        Map root = new HashMap();
        
        root.put("registry",registry);
        //root.put("termName", new TermName(miOntologyTree));
        root.put("termName", new TermName(miOntologyTree));
        
        try {
            template.process(root, writer);
        } catch (TemplateException e) {
            throw new RuntimeException("Problem processing freemarker template");
        }

        writer.close();
        outputStream.close();
    }
}
