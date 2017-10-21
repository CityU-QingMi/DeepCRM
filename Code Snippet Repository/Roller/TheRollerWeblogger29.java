    public boolean equals(Object other) {        
        if (this == other) {
            return true;
        }
        if (!(other instanceof Subscription)) {
            return false;
        }
        final Subscription that = (Subscription) other;
        return this.feedUrl.equals(that.getFeedURL());
    }
