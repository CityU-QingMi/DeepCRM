    public String delete() {
        
        if(getSubUrl() != null) {
            try {

                PlanetManager pmgr = WebloggerFactory.getWeblogger().getPlanetManager();

                // remove subscription
                Subscription sub = pmgr.getSubscription(getSubUrl());
                getGroup().getSubscriptions().remove(sub);
                sub.getGroups().remove(getGroup());
                pmgr.saveGroup(getGroup());
                WebloggerFactory.getWeblogger().flush();

                // clear field after success
                setSubUrl(null);

                addMessage("planetSubscription.success.deleted");

            } catch (RollerException ex) {
                LOGGER.error("Error removing planet subscription", ex);
                addError("planetSubscription.error.deleting");
            }
        }

        return LIST;
    }
