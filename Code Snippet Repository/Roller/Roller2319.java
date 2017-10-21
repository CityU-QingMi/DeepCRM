    public String save() {
        myValidate();

        if(!hasActionErrors()) {
            try {
                getBean().copyTo(bookmark);
                BookmarkManager bmgr = WebloggerFactory.getWeblogger().getBookmarkManager();
                bmgr.saveBookmark(bookmark);
                WebloggerFactory.getWeblogger().flush();
                CacheManager.invalidate(bookmark);
                addMessage(isAdd() ? "bookmarkForm.created" : "bookmarkForm.updated",
                        getBookmark().getName());
                return SUCCESS;

            } catch(Exception ex) {
                log.error("Error saving bookmark", ex);
                addError("generic.error.check.logs");
            }
        }
        
        return INPUT;
    }
