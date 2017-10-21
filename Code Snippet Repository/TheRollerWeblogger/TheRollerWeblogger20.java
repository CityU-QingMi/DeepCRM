    private void updateSubscriptions(Collection<Subscription> subscriptions) {
        
        PlanetManager pmgr = WebloggerFactory.getWeblogger().getPlanetManager();
		for (Subscription sub : subscriptions) {
			try {
				// reattach sub.  sub gets detached as we iterate
				sub = pmgr.getSubscriptionById(sub.getId());
			} catch (RollerException ex) {
				log.warn("Subscription went missing while doing update: "+ex.getMessage());
			}
			
			// this updates and saves
			try {
				updateSubscription(sub);
			} catch(UpdaterException ex) {
				// do a little work to get at the source of the problem
				Throwable cause = ex;
				if(ex.getRootCause() != null) {
					cause = ex.getRootCause();
				}
				if(cause.getCause() != null) {
					cause = cause.getCause();
				}
				
				if (log.isDebugEnabled()) {
					log.debug("Error updating subscription - "+sub.getFeedURL(), cause);
				} else {
					log.warn("Error updating subscription - "+sub.getFeedURL()
						+ " turn on debug logging for more info");
				}
				
			} catch(Exception ex) {
				if (log.isDebugEnabled()) {
					log.warn("Error updating subscription - "+sub.getFeedURL(), ex);
				} else {
					log.warn("Error updating subscription - "+sub.getFeedURL()
						+ " turn on debug logging for more info");
				}
			}
		}
    }
