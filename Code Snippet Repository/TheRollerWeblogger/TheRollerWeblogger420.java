    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof WeblogEntryAttribute)) {
            return false;
        }
        WeblogEntryAttribute o = (WeblogEntryAttribute)other;
        return new EqualsBuilder()
        .append(getName(), o.getName())
        .append(getEntry(), o.getEntry())
        .isEquals();
    }
