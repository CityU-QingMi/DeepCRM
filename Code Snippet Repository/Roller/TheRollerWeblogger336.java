    public String delete() {
        
        if(getGroup() != null) {
            try {
                PlanetManager pmgr = WebloggerFactory.getWeblogger().getPlanetManager();
                pmgr.deleteGroup(getGroup());
                WebloggerFactory.getWeblogger().flush();
                
                addMessage("planetSubscription.success.deleted");
            } catch(Exception ex) {
                log.error("Error deleting planet group - "+getBean().getId());
                addError("Error deleting planet group");
            }
        }
        
        return LIST;
    }
