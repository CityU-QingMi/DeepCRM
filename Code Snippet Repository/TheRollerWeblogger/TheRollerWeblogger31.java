    public boolean equals(Object other) {        
        if (this == other) {
            return true;
        }
        if (!(other instanceof SubscriptionEntry)) {
            return false;
        }
        final SubscriptionEntry that = (SubscriptionEntry) other;
        return getPermalink().equals(that.getPermalink());
    }
