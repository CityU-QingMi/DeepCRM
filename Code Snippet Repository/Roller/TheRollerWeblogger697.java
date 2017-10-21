    public String execute() {
        try {
            // Build list of categories that the removed category's blog entries (if any) can be moved to
            WeblogEntryManager wmgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
            List<WeblogCategory> cats = wmgr.getWeblogCategories(getActionWeblog());
            for (WeblogCategory cat : cats) {
                if (!cat.getId().equals(getRemoveId())) {
                    allCategories.add(cat);
                }
            }
        } catch (WebloggerException ex) {
            log.error("Error building categories list", ex);
            addError("generic.error.check.logs");
        }
        return INPUT;
    }
