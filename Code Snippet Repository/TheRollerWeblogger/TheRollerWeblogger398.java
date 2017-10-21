    public boolean equals(Object other) {
        
        if (other == null) {
            return false;
        }
        
        if (other instanceof WeblogBookmarkFolder) {
            WeblogBookmarkFolder o = (WeblogBookmarkFolder) other;
            return new EqualsBuilder()
                .append(getName(), o.getName())
                .append(getWeblog(), o.getWeblog())
                .isEquals();
        }
        
        return false;
    }    
