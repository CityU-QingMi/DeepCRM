    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof WeblogEntryTagAggregate)) {
            return false;
        }
        WeblogEntryTagAggregate o = (WeblogEntryTagAggregate)other;
        return new EqualsBuilder()
        .append(getName(), o.getName())
        .append(this.getWeblog(), o.getWeblog())
        .isEquals();
    }
