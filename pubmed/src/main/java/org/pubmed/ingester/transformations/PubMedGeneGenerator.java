package org.pubmed.ingester.transformations;

import org.pubmed.ingester.domain.GenePubMedEntity;
import org.pubmed.ingester.domain.PubMedEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.tuple.Values;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

public class PubMedGeneGenerator extends BaseFunction {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory
			.getLogger(PubMedGeneGenerator.class);
	
	@Override
	public void execute(TridentTuple tuple, TridentCollector collector) {
		String geneId = tuple.getStringByField("geneId");
		String pubmedId = tuple.getStringByField("pubMedId");
		
		GenePubMedEntity entity = new GenePubMedEntity(geneId, pubmedId);
		
		log.info("Entity:" + entity);
		
		collector.emit(new Values(entity));
		
	}

}
