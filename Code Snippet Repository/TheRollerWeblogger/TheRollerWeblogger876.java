    private void reindexEntry(WeblogEntry entry) throws WebloggerException {
        IndexManager manager = roller.getIndexManager();
        
        // TODO: figure out what's up here and at WeblogEntryFormAction line 696
        //manager.removeEntryIndexOperation(entry);
        
        // if published, index the entry
        if (entry.isPublished()) {
            manager.addEntryReIndexOperation(entry);
        }
    }
