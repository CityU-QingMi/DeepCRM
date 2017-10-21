    public void myPrepare() {
        if (StringUtils.isEmpty(bean.getId())) {
            // Create and initialize new, not-yet-saved category
            category = new WeblogCategory();
            category.setWeblog(getActionWeblog());
        } else {
            try {
                WeblogEntryManager wmgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
                category = wmgr.getWeblogCategory(getBean().getId());
            } catch (WebloggerException ex) {
                log.error("Error looking up category", ex);
            }
        }
    }
