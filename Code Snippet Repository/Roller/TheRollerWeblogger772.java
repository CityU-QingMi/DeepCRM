    public String remove() {

        try {

            // remove website
            WebloggerFactory.getWeblogger().getWeblogManager().removeWeblog(getActionWeblog());
            WebloggerFactory.getWeblogger().flush();

            CacheManager.invalidate(getActionWeblog());

            addMessage("websiteRemove.success", getActionWeblog().getName());

            return SUCCESS;
        } catch (Exception ex) {
            log.error("Error removing weblog - " + getActionWeblog().getHandle(), ex);
            addError("websiteRemove.error", getActionWeblog().getName());
        }

        return "confirm";
    }
