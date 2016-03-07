package org.pubmed.ingester.domain;

import org.pubmed.ingester.pubmedreader.PubMedXMLRetriever;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenePubMedEntity {
	
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(GenePubMedEntity.class);
	private String geneId;
	private String pubMedId;
	
	public GenePubMedEntity(){
		
	}
	
	public GenePubMedEntity(String geneId, String pubMedId){
		this.geneId = geneId;
		this.pubMedId = pubMedId;
	}

	public String getGeneId() {
		return geneId;
	}

	public void setGeneId(String geneId) {
		this.geneId = geneId;
	}

	public String getPubMedId() {
		return pubMedId;
	}

	public void setPubMedId(String pubMedId) {
		this.pubMedId = pubMedId;
	}

	@Override
	public String toString() {
		return "GenePubMedEntity [geneId=" + geneId + ", pubMedId=" + pubMedId
				+ "]";
	}
	
	
}
