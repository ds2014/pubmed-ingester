package org.pubmed.ingester.transformations;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

import org.pubmed.ingester.domain.GenePubMedEntity;
import org.pubmed.ingester.domain.PubMedEntity;
import org.pubmed.ingester.pubmedreader.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.tuple.Values;

public class PubMedFetcher extends BaseFunction {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory
			.getLogger(PubMedEntity.class);

	@Override
	public void execute(TridentTuple tuple, TridentCollector collector) {
		GenePubMedEntity object = (GenePubMedEntity) tuple
				.getValueByField("genePubMeds");
		Exception exception = null;

		String pubMedId = null;
		String publicationDocument = null;

		try {
			pubMedId = object.getPubMedId();
			publicationDocument = new PubMedService().getPubMedDocument(pubMedId);
		} catch (Exception e) {
			exception = e;
		} finally {

			if (exception != null) {
				log.error("PubMedFetcher. Error retrieving Publication for PubMedId:"
						+ pubMedId);
			} else {

				log.info("PubMedFetcher. Publication for PubMedID:"
						+ publicationDocument);

				collector.emit(new Values(publicationDocument));

			}
		}

	}

}
