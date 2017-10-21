    private IndexOperation getSaveIndexOperation() {
        return new WriteToIndexOperation(this) {
            public void doRun() {
                Directory dir = getIndexDirectory();
                Directory fsdir = getFSDirectory(true);
                IndexWriter writer = null;
                try {
                    IndexWriterConfig config = new IndexWriterConfig(FieldConstants.LUCENE_VERSION,
                            new LimitTokenCountAnalyzer(IndexManagerImpl.getAnalyzer(),
                                    IndexWriterConfig.DEFAULT_TERM_INDEX_INTERVAL));
                    writer = new IndexWriter(fsdir, config);
                    writer.addIndexes(new Directory[] { dir });
                    writer.commit();
                    indexConsistencyMarker.delete();
                } catch (IOException e) {
                    mLogger.error("Problem saving index to disk", e);
                    // Delete the directory, since there was a problem saving the RAM contents
                    getFSDirectory(true);
                } finally {
                    try {
                        if (writer != null) {
                            writer.close();
                        }
                    } catch (IOException e1) {
                        mLogger.warn("Unable to close IndexWriter.");
                    }
                }
            }
        };
    }
