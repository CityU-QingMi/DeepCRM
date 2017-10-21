    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof OAuthAccessorRecord)) {
            return false;
        }
        OAuthAccessorRecord o = (OAuthAccessorRecord)other;
        return new EqualsBuilder()
            .append(getConsumerKey(), o.getConsumerKey())
            .isEquals();
    }
