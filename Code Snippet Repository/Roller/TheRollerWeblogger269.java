    public void doRun() {
        IndexWriter writer = beginWriting();
        
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
        
        try {
            if (writer != null) {
                writer.addDocument(getDocument(data));
            }
        } catch (IOException e) {
            mLogger.error("Problems adding doc to index", e);
        } finally {
            if (roller != null) {
                roller.release();
            }
            endWriting();
        }
    }   
