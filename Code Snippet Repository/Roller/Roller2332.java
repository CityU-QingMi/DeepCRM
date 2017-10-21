    public String remove() {
        
        if(getCategory() != null) {
            try {
                WeblogEntryManager wmgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();

                if (getTargetCategoryId() != null) {
                    WeblogCategory target = wmgr.getWeblogCategory(getTargetCategoryId());
                    wmgr.moveWeblogCategoryContents(getCategory(), target);
                    WebloggerFactory.getWeblogger().flush();
                }

                // notify cache
                CacheManager.invalidate(getCategory());

                wmgr.removeWeblogCategory(getCategory());
                WebloggerFactory.getWeblogger().flush();
                addMessage("categoryForm.removed", category.getName());
                return SUCCESS;
            } catch(Exception ex) {
                log.error("Error removing category - " + getRemoveId(), ex);
                addError("generic.error.check.logs");
            }
        }
        
        return execute();
    }
