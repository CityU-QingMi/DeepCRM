    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof WeblogEntryComment)) {
            return false;
        }
        WeblogEntryComment o = (WeblogEntryComment)other;
        return new EqualsBuilder()
            .append(getName(), o.getName()) 
            .append(getPostTime(), o.getPostTime()) 
            .append(getWeblogEntry(), o.getWeblogEntry()) 
            .isEquals();
    }
