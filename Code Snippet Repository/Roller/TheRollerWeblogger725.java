    public String save() {
        myValidate();
        
        if(!hasActionErrors()) {
            try {
                // copy updated attributes
                getBean().copyTo(folder);

                // save changes
                BookmarkManager bmgr = WebloggerFactory.getWeblogger().getBookmarkManager();
                bmgr.saveFolder(folder);
                WebloggerFactory.getWeblogger().flush();

                // notify caches
                CacheManager.invalidate(folder);

                if (isAdd()) {
                    // if adding, move to new folder upon save.
                    folderId = folder.getId();
                } else {
                    // create message added in Bookmarks class as it's reached via a
                    // redirect in struts.xml instead of a chain.
                    addMessage("folderForm.updated");
                }

                return SUCCESS;

            } catch(Exception ex) {
                log.error("Error saving folder", ex);
                addError("generic.error.check.logs");
            }
        }
        
        return INPUT;
    }
