    public void doRun() {

        // since this operation can be run on a separate thread we must treat
        // the weblog object passed in as a detached object which is prone to
        // lazy initialization problems, so requery for the object now
        try {
            WeblogEntryManager wMgr = roller.getWeblogEntryManager();
            this.data = wMgr.getWeblogEntry(this.data.getId());
        } catch (WebloggerException ex) {
            mLogger.error("Error getting weblogentry object", ex);
            return;
        }

        IndexWriter writer = beginWriting();
        try {
            if (writer != null) {

                // Delete Doc
                Term term = new Term(FieldConstants.ID, data.getId());
                writer.deleteDocuments(term);

                // Add Doc
                writer.addDocument(getDocument(data));
            }
        } catch (IOException e) {
            mLogger.error("Problems adding/deleting doc to index", e);
        } finally {
            if (roller != null) {
                roller.release();
            }
            endWriting();
        }
    }
