package org.pubmed.ingester.pubmedreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PubMedXMLRetriever {

	private static final Logger log = LoggerFactory.getLogger(PubMedXMLRetriever.class);
	private final static String QUERY_PARAM = "id";
		
	public PubMedXMLRetriever(){
		
	}
	
	public static String fetchPubMed(final String pubMedId)
			throws ClientProtocolException, IOException, URISyntaxException {

		CloseableHttpClient client = HttpClients.createDefault();
		URI uri = new URIBuilder().setScheme("http")
				.setHost("eutils.ncbi.nlm.nih.gov")
				.setPath("/entrez/eutils/efetch.fcgi")
				.setParameter("db", "pubmed").setParameter("retmode", "xml")
				.setParameter("rettype", "abstract")
				.setParameter("id", pubMedId).build();

		log.info("Uri:"  + uri);
		HttpGet request = new HttpGet(uri);
		CloseableHttpResponse response = client.execute(request);
		BufferedReader resultStream = null;
        String result = null;
		
		try {
			resultStream = new BufferedReader(new InputStreamReader(response
					.getEntity().getContent()));
			result = IOUtils.toString(resultStream);
			log.debug("Document Result:" + result);
		} finally {
			response.close();
		}

		
		return result;

	}
}
