    public String save() {
        
        myValidate();
        
        if(!hasActionErrors()) {
            try {
                PlanetManager pmgr = WebloggerFactory.getWeblogger().getPlanetManager();

                // check if this subscription already exists before adding it
                Subscription sub = pmgr.getSubscription(getSubUrl());
                if(sub == null) {
                    LOGGER.debug("Adding New Subscription - " + getSubUrl());

                    // sub doesn't exist yet, so we need to fetch it
                    FeedFetcher fetcher = WebloggerFactory.getWeblogger().getFeedFetcher();
                    sub = fetcher.fetchSubscription(getSubUrl());

                    // save new sub
                    pmgr.saveSubscription(sub);
                } else {
                    LOGGER.debug("Adding Existing Subscription - " + getSubUrl());

                    // Subscription already exists
                    addMessage("planetSubscription.foundExisting", sub.getTitle());
                }

                // add the sub to the group
                group.getSubscriptions().add(sub);
                sub.getGroups().add(group);
                pmgr.saveGroup(group);

                // flush changes
                WebloggerFactory.getWeblogger().flush();

                // clear field after success
                setSubUrl(null);

                addMessage("planetSubscription.success.saved");

            } catch (RollerException ex) {
                LOGGER.error("Unexpected error saving subscription", ex);
                addError("planetSubscriptions.error.duringSave", ex.getRootCauseMessage());
            }
        }
        
        return LIST;
    }
