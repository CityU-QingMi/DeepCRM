    public void doRun() {

        // since this operation can be run on a separate thread we must treat
        // the weblog object passed in as a detached object which is proned to
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
                Term term = new Term(FieldConstants.ID, data.getId());
                writer.deleteDocuments(term);
            }
        } catch (IOException e) {
            mLogger.error("Error deleting doc from index", e);
        } finally {
            endWriting();
        }
    }
