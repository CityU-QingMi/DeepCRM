    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof WeblogBookmark)) {
            return false;
        }
        WeblogBookmark o = (WeblogBookmark)other;
        return new EqualsBuilder()
        .append(getName(), o.getName())
        .append(getFolder(), o.getFolder())
        .append(getUrl(), o.getUrl())
        .isEquals();
    }
