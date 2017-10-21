    public String publish() {
        if (getActionWeblog().hasUserPermission(
                getAuthenticatedUser(), WeblogPermission.POST)) {
            Timestamp pubTime = getBean().getPubTime(getLocale(),
                    getActionWeblog().getTimeZoneInstance());
            if (pubTime != null && pubTime.after(
                    new Date(System.currentTimeMillis() + RollerConstants.MIN_IN_MS))) {
                getBean().setStatus(PubStatus.SCHEDULED.name());
                if (entry.isPublished()) {
                    // entry went from published to scheduled, need to reduce tag aggregates
                    entry.setRefreshAggregates(true);
                }
            } else {
                getBean().setStatus(PubStatus.PUBLISHED.name());
                if (getBean().getId() != null && !entry.isPublished()) {
                    // if not a new add, need to add tags to aggregates
                    entry.setRefreshAggregates(true);
                }
            }
        } else {
            getBean().setStatus(PubStatus.PENDING.name());
        }
        return save();
    }
