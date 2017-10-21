    public String save() {
        myValidate();
        
        if(!hasActionErrors()) {
            try {

                // copy updated attributes
                getBean().copyTo(category);

                // save changes
                WeblogEntryManager wmgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
                if (isAdd()) {
                    getActionWeblog().addCategory(category);
                    category.calculatePosition();
                }
                wmgr.saveWeblogCategory(category);
                WebloggerFactory.getWeblogger().flush();

                // notify caches
                CacheManager.invalidate(getActionWeblog());

                addMessage(isAdd()? "categoryForm.created"
                        : "categoryForm.changesSaved",
                        category.getName());

                return SUCCESS;
            } catch(Exception ex) {
                log.error("Error saving category", ex);
                addError("generic.error.check.logs");
            }
        }
        
        return INPUT;
    }
