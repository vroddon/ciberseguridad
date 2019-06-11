package oeg.ciberseguridad.core;

import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        String json = getPatents("cyber security", "2018");
        System.out.println(json);
    }

    public static String getPatents(String year) {
        return getPatents("cyber security", year);
    }
    
    public static String getPatents(String keyword, String year) {
        String prefijos = "prefix cpc: <http://data.epo.org/linked-data/def/cpc/> prefix dcterms: <http://purl.org/dc/terms/> prefix ipc: <http://data.epo.org/linked-data/def/ipc/> prefix mads: <http://www.loc.gov/standards/mads/rdf/v1.rdf> prefix owl: <http://www.w3.org/2002/07/owl#> prefix patent: <http://data.epo.org/linked-data/def/patent/> prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> prefix skos: <http://www.w3.org/2004/02/skos/core#> prefix st3: <http://data.epo.org/linked-data/def/st3/> prefix text: <http://jena.apache.org/text#> prefix vcard: <http://www.w3.org/2006/vcard/ns#> prefix xsd: <http://www.w3.org/2001/XMLSchema#>";
        String sparql = "SELECT DISTINCT ?pais (COUNT(?publication) AS ?total) {\n"
                + "  ?publication text:query ( dcterms:abstract \"" + keyword + "\" ) .\n"
                + "  ?publication dcterms:abstract ?abstract .\n"
                + "  ?publication patent:titleOfInvention ?title .\n"
                + "  ?publication patent:applicantVC ?applicant .\n"
                + "  ?applicant vcard:hasAddress ?address .\n"
                + "  ?address patent:countryCode ?pais .\n"
                + "  ?publication patent:publicationDate ?date .\n"
                + "  FILTER (datatype(?date) = xsd:date && year(?date) = " + year + ")\n"
                + "  FILTER (lang(?title) = 'en')\n"
                + "} GROUP BY ?pais ORDER BY DESC(?total)";
        System.out.println(sparql);
        Query query = QueryFactory.create(prefijos + sparql);
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        Map<String, String> map = new HashMap();
        try {
            ResultSet results = qexec.execSelect();
            for (; results.hasNext();) {
                QuerySolution qs = results.next();
                String total = qs.get("?total").asLiteral().getLexicalForm();
                String pais = qs.get("?pais").asLiteral().toString();
                map.put(pais, total);
            }

            Gson gson = new Gson();
            String json = gson.toJson(map);

            return json;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            qexec.close();
        }
        return "{}";
    }

    public static String getPatentsIneficiente(String keyword, String year) {
        String prefijos = "prefix cpc: <http://data.epo.org/linked-data/def/cpc/> prefix dcterms: <http://purl.org/dc/terms/> prefix ipc: <http://data.epo.org/linked-data/def/ipc/> prefix mads: <http://www.loc.gov/standards/mads/rdf/v1.rdf> prefix owl: <http://www.w3.org/2002/07/owl#> prefix patent: <http://data.epo.org/linked-data/def/patent/> prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> prefix skos: <http://www.w3.org/2004/02/skos/core#> prefix st3: <http://data.epo.org/linked-data/def/st3/> prefix text: <http://jena.apache.org/text#> prefix vcard: <http://www.w3.org/2006/vcard/ns#> prefix xsd: <http://www.w3.org/2001/XMLSchema#>";
        String sparql = "SELECT DISTINCT ?publication ?title ?pais (year(xsd:dateTime(?date)) as ?anyo) {\n"
                + "  ?publication text:query ( dcterms:abstract \"" + keyword + "\" ) .\n"
                + "  ?publication dcterms:abstract ?abstract .\n"
                + "  ?publication patent:titleOfInvention ?title .\n"
                + "  ?publication patent:applicantVC ?applicant .\n"
                + "  ?applicant vcard:hasAddress ?address .\n"
                + "  ?address patent:countryCode ?pais .\n"
                + "  ?publication patent:publicationDate ?date .\n"
                + "  FILTER (datatype(?date) = xsd:date && year(?date) = " + year + ")\n"
                + "  FILTER (lang(?title) = 'en')\n"
                + "} LIMIT 100";
        System.out.println(sparql);
        Query query = QueryFactory.create(prefijos + sparql);
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        Map<String, String> map = new HashMap();
        try {
            ResultSet results = qexec.execSelect();
            for (; results.hasNext();) {
                QuerySolution qs = results.next();
                String titulo = qs.get("?title").asLiteral().toString();
                String pais = qs.get("?pais").asLiteral().toString();
                String num = map.get(pais);
                if (num == null) {
                    num = "0";
                }
                int inum = Integer.parseInt(num);
                inum++;
                num = "" + inum;
                map.put(pais, num);
                //       System.out.println(pais + " " + titulo);
            }

            Gson gson = new Gson();
            String json = gson.toJson(map);

            return json;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            qexec.close();
        }
        return "{}";
    }
}
