    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof WeblogEntryTag)) {
            return false;
        }
        WeblogEntryTag o = (WeblogEntryTag)other;
        return new EqualsBuilder()
        .append(getName(), o.getName())
        .append(getWeblogEntry(), o.getWeblogEntry())
        .isEquals();
    }
