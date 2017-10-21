    private void createIndex(Directory dir) {
        IndexWriter writer = null;

        try {

            IndexWriterConfig config = new IndexWriterConfig(
                    FieldConstants.LUCENE_VERSION, new LimitTokenCountAnalyzer(
                            IndexManagerImpl.getAnalyzer(),
                            IndexWriterConfig.DEFAULT_TERM_INDEX_INTERVAL));

            writer = new IndexWriter(dir, config);

        } catch (IOException e) {
            mLogger.error("Error creating index", e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
            }
        }
    }
