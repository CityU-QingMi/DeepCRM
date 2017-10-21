    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof PingQueueEntry)) {
            return false;
        }
        PingQueueEntry o = (PingQueueEntry)other;
        return new EqualsBuilder()
            .append(getEntryTime(), o.getEntryTime()) 
            .append(getWebsite(), o.getWebsite()) 
            .isEquals();
    }
