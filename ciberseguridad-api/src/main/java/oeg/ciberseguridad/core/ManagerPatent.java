package oeg.ciberseguridad.core;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

/**
 *
 * @author vroddon
 */
public class ManagerPatent {

    public static String endpoint = "https://data.epo.org/linked-data/query";

    public static void main(String[] args) {
        getPatents("2018");
    }

    public static void getPatents(String year) {
        String prefijos = "prefix cpc: <http://data.epo.org/linked-data/def/cpc/> prefix dcterms: <http://purl.org/dc/terms/> prefix ipc: <http://data.epo.org/linked-data/def/ipc/> prefix mads: <http://www.loc.gov/standards/mads/rdf/v1.rdf> prefix owl: <http://www.w3.org/2002/07/owl#> prefix patent: <http://data.epo.org/linked-data/def/patent/> prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> prefix skos: <http://www.w3.org/2004/02/skos/core#> prefix st3: <http://data.epo.org/linked-data/def/st3/> prefix text: <http://jena.apache.org/text#> prefix vcard: <http://www.w3.org/2006/vcard/ns#> prefix xsd: <http://www.w3.org/2001/XMLSchema#>";

        String sparql = "SELECT DISTINCT ?publication ?title ?pais {\n"
                + "  ?publication text:query ( dcterms:abstract \"cyber security\" ) .\n"
                + "  ?publication dcterms:abstract ?abstract .\n"
                + "  ?publication patent:titleOfInvention ?title .\n"
                + "  ?publication patent:applicantVC ?applicant .\n"
                + "  ?applicant vcard:hasAddress ?address .\n"
                + "  ?address patent:countryCode ?pais .\n"
                + "  FILTER (lang(?title) = 'en')\n"
                + "} LIMIT 100";
        Query query = QueryFactory.create(prefijos + sparql);
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        List<String> candidatos = new ArrayList();
        try {
            ResultSet results = qexec.execSelect();
            for (; results.hasNext();) {
                QuerySolution qs = results.next();
                String titulo = qs.get("?title").asLiteral().toString();
                String pais = qs.get("?pais").asLiteral().toString();
                System.out.println(pais + " " + titulo);
            }
            return;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            qexec.close();
        }
        return;
    }
}
