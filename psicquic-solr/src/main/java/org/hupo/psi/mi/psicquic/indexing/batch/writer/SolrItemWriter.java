package org.hupo.psi.mi.psicquic.indexing.batch.writer;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.hupo.psi.calimocho.model.Row;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemWriter;
import org.xml.sax.SAXException;
import psidev.psi.mi.calimocho.solr.converter.Converter;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Solr item writer
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>29/05/12</pre>
 */

public class SolrItemWriter implements ItemWriter<Row>, ItemStream {

    protected String solrUrl;
    protected HttpSolrServer solrServer;
    protected Converter solrConverter;

    public SolrItemWriter(){
        solrConverter = new Converter();
    }

    /**
     * Index a list of calimocho rows in SOLR
     * @param items
     * @throws Exception
     */
    public void write(List<? extends Row> items) throws Exception {

        if (solrUrl == null) {
            throw new IllegalStateException("No 'solrURL' configured for SolrItemWriter");
        }

        if (items.isEmpty()) {
            return;
        }

        for (Row binaryInteraction : items) {

            SolrInputDocument solrInputDoc = solrConverter.toSolrDocument(binaryInteraction);
            solrServer.add(solrInputDoc);
        }
    }

    public void open(ExecutionContext executionContext) throws ItemStreamException {
        try {
            if (solrUrl != null) {
                try {
                    createSolrServer();
                } catch (SAXException e) {
                    new ItemStreamException("Impossible to create a new embedded solr server",e);
                } catch (ParserConfigurationException e) {
                    new ItemStreamException("Impossible to create a new embedded solr server",e);
                }
            }
        } catch (IOException e) {
            throw new ItemStreamException("Problem with ontology solr server: "+ solrUrl, e);
        }
    }

    public void update(ExecutionContext executionContext) throws ItemStreamException {
        if (solrServer != null){
            try {
                solrServer.commit();
            } catch (SolrServerException e) {
                throw new ItemStreamException("Problem committing the results.", e);
            } catch (IOException e) {
                throw new ItemStreamException("Problem committing the results.", e);
            }
        }
    }

    public void close() throws ItemStreamException {
        if (solrServer != null){
            try {
                solrServer.optimize();
            } catch (Exception e) {
                throw new ItemStreamException("Problem closing solr server", e);
            }

            solrServer.shutdown();
            this.solrServer = null;
        }
    }

    public SolrServer createSolrServer() throws IOException, SAXException, ParserConfigurationException {
        if (solrServer == null) {
            if (solrUrl == null) {
                throw new NullPointerException("No 'solr url' configured for SolrItemWriter");
            }

            solrServer = new HttpSolrServer(solrUrl);
        }

        return solrServer;
    }

    public void setSolrUrl(String solrUrl) {
        this.solrUrl = solrUrl;
    }
}
