    public String delete() {
        
        try {
            WeblogEntryManager wmgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
            int deleted = wmgr.removeMatchingComments(
                    null,
                    null,
                    getBean().getSearchString(),
                    getBean().getStartDate(),
                    getBean().getEndDate(),
                    getBean().getStatus());
            
            addMessage("commentManagement.deleteSuccess",Integer.toString(deleted));
            
            // reset form and load fresh comments list
            setBean(new GlobalCommentManagementBean());
            
            return execute();
            
        } catch (WebloggerException ex) {
            log.error("Error doing bulk delete", ex);
            addError("commentManagement.deleteError");
        }
        
        return LIST;
    }
