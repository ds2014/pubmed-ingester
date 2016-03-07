package org.pubmed.ingester.domain;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneEntity {

	private String geneId;
	private String symbol;
	List<PubMedEntity> pubmeds = new ArrayList<PubMedEntity>();
	
	private static final Logger log = LoggerFactory
			.getLogger(PubMedEntity.class);

	public GeneEntity(){
		
	}
	
	public String getGeneId() {
		return geneId;
	}

	public void setGeneId(String geneId) {
		this.geneId = geneId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public List<PubMedEntity> getPubmeds() {
		return pubmeds;
	}

	public void setPubmeds(List<PubMedEntity> pubmeds) {
		this.pubmeds = pubmeds;
	}

	@Override
	public String toString() {
		return "GeneEntity [geneId=" + geneId + ", symbol=" + symbol
				+ ", pubmeds=" + pubmeds + "]";
	}
	
	
	
}
