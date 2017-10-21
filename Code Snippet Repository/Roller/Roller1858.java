    protected IndexWriter beginWriting() {
        try {

            // Limit to 1000 tokens.
            LimitTokenCountAnalyzer analyzer = new LimitTokenCountAnalyzer(
                    IndexManagerImpl.getAnalyzer(), 1000);

            IndexWriterConfig config = new IndexWriterConfig(
                    FieldConstants.LUCENE_VERSION, analyzer);

            writer = new IndexWriter(manager.getIndexDirectory(), config);

        } catch (IOException e) {
            mLogger.error("ERROR creating writer", e);
        }

        return writer;
    }
