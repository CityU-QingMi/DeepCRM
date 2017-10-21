    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof OAuthConsumerRecord)) {
            return false;
        }
        OAuthConsumerRecord o = (OAuthConsumerRecord)other;
        return new EqualsBuilder()
            .append(getConsumerKey(), o.getConsumerKey())
            .isEquals();
    }
