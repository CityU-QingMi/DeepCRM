    public String remove() {

        if (getRemoveEntry() != null) {
            try {
                WeblogEntry entry = getRemoveEntry();
                IndexManager manager = WebloggerFactory.getWeblogger().getIndexManager();

                try {
                    // remove the entry from the search index
                    // TODO: can we do this in a better way?
                    WeblogEntry.PubStatus originalStatus = entry.getStatus();
                    entry.setStatus(WeblogEntry.PubStatus.DRAFT);
                    manager.addEntryReIndexOperation(entry);
                    entry.setStatus(originalStatus);
                } catch (WebloggerException ex) {
                    log.warn("Trouble triggering entry indexing", ex);
                }

                // remove from search index
                if (entry.isPublished()) {
                    manager.removeEntryIndexOperation(entry);
                }

                // flush caches
                CacheManager.invalidate(entry);

                // remove entry itself
                WeblogEntryManager wmgr = WebloggerFactory.getWeblogger()
                        .getWeblogEntryManager();
                wmgr.removeWeblogEntry(entry);
                WebloggerFactory.getWeblogger().flush();

                // note to user
                addMessage("weblogEdit.entryRemoved", entry.getTitle());

                return SUCCESS;

            } catch (Exception e) {
                log.error("Error removing entry " + getRemoveId(), e);
                addError("generic.error.check.logs");
            }
        } else {
            addError("weblogEntry.notFound");
            return ERROR;
        }

        return INPUT;
    }
