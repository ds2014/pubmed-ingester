package org.pubmed.ingester.pubmedreader;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.pubmed.ingester.domain.GenePubMedEntity;
import org.pubmed.ingester.domain.PubMedEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class GenePubMedListener extends BaseRichSpout {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory
			.getLogger(GenePubMedListener.class);

	private SpoutOutputCollector outputCollector;
	private LinkedBlockingQueue<GenePubMedEntity> genePubMedQueue = new LinkedBlockingQueue<GenePubMedEntity>();

	public GenePubMedListener() {
		
	}

	public GenePubMedListener(LinkedBlockingQueue queue) {
	}

	@Override
	public void nextTuple() {

		GenePubMedEntity entity = genePubMedQueue.poll();
		
		GenePubMedEntity gpm1 = new GenePubMedEntity("AT3G24650", "26941772");
		GenePubMedEntity gpm2 = new GenePubMedEntity("AT3G24650", "26869511");

		if (entity != null) {
			log.info("Emitting GenePubMedId Entity:" + entity);
			outputCollector.emit(new Values(entity));
		}

	}

	@Override
	public void open(Map map, TopologyContext context,
			SpoutOutputCollector collector) {
		outputCollector = collector;

	}

	@Override
	// emit geneId, pubmedId
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("genePubMeds"));
	}

	private void init() {

		GenePubMedEntity gpm1 = new GenePubMedEntity("AT3G24650", "26941772");
		GenePubMedEntity gpm2 = new GenePubMedEntity("AT3G24650", "26869511");

		genePubMedQueue.add(gpm1);
		genePubMedQueue.add(gpm2);

	}

}
