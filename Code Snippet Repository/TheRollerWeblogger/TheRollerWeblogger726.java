    public String index() {

        try {
            IndexManager manager = WebloggerFactory.getWeblogger()
                    .getIndexManager();
            manager.rebuildWebsiteIndex(getActionWeblog());

            addMessage("maintenance.message.indexed");
        } catch (Exception ex) {
            log.error("Error doing index rebuild", ex);
            addError("maintenance.message.indexed.failure");
        }

        return SUCCESS;
    }
