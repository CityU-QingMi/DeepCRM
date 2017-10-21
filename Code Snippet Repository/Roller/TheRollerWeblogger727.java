    public String flushCache() {

        try {
            Weblog weblog = getActionWeblog();

            // some caches are based on weblog last-modified, so update it
            weblog.setLastModified(new Date());

            WebloggerFactory.getWeblogger().getWeblogManager()
                    .saveWeblog(weblog);
            WebloggerFactory.getWeblogger().flush();

            // also notify cache manager
            CacheManager.invalidate(weblog);

            addMessage("maintenance.message.flushed");

        } catch (Exception ex) {
            log.error("Error saving weblog - " + getActionWeblog().getHandle(),
                    ex);
            addError("Error flushing page cache");
        }

        return SUCCESS;
    }
