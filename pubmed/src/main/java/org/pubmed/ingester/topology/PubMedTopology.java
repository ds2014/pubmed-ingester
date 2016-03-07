package org.pubmed.ingester.topology;

import org.pubmed.ingester.domain.GenePubMedEntity;
import org.pubmed.ingester.pubmedreader.GenePubMedListener;
import org.pubmed.ingester.transformations.PubMedFetcher;
import org.pubmed.ingester.transformations.PubMedGeneGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.LocalDRPC;
import backtype.storm.generated.StormTopology;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import storm.trident.Stream;
import storm.trident.TridentTopology;
import storm.trident.testing.FixedBatchSpout;

public class PubMedTopology {

	private static final Logger log = LoggerFactory
			.getLogger(PubMedTopology.class);

	public static StormTopology createTopology(LocalDRPC drpc) {
		TridentTopology topology = new TridentTopology();

		Exception exception = null;

		FixedBatchSpout initialData = new FixedBatchSpout(new Fields("geneId",
				"pubMedId"), 10, new Values("AT3G24650", "26941772"));
		initialData.setCycle(true);

		Stream documentIdentifiers = topology.newStream("data", initialData);

		Stream genePubMedStream = topology.newStream("genePubMedIds",
				initialData).each(new Fields("geneId", "pubMedId"),
				new PubMedGeneGenerator(), new Fields("genePubMeds"));

		try {

			Stream publicationDocumentStream = genePubMedStream.each(
					new Fields("genePubMeds"), new PubMedFetcher(), new Fields(
							"document"));

		} catch (Exception e) {
			exception = e;
		} finally {

			if (exception != null) {
				log.error("Error retrieving Publication");
			}
		}

		return topology.build();
	}

	public static void main(String[] args) {

		Config config = new Config();

		if (args.length == 0) {
			LocalDRPC drpc = new LocalDRPC();
			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("pubMedService", config,
					createTopology(null));
		}
	}
}
