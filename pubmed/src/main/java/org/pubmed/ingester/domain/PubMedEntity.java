package org.pubmed.ingester.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PubMedEntity {

	private String pubMedId;
	private String geneId;
	private String title;
	private String author;
	private String year;
	private String abstractTitle;

	private static final Logger log = LoggerFactory
			.getLogger(PubMedEntity.class);

	public PubMedEntity(){
		
	}
	
	public String getGeneId() {
		return geneId;
	}

	public void setGeneId(String geneId) {
		this.geneId = geneId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getAbstractTitle() {
		return abstractTitle;
	}

	public void setAbstractTitle(String abstractTitle) {
		this.abstractTitle = abstractTitle;
	}

	public String getPubMedId() {
		return pubMedId;
	}

	public void setPubMedId(String pubMedId) {
		this.pubMedId = pubMedId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "PubMedEntity [pubMedId=" + pubMedId + ", geneId=" + geneId
				+ ", title=" + title + ", author=" + author + ", year=" + year
				+ ", abstractTitle=" + abstractTitle + "]";
	}

	
}
