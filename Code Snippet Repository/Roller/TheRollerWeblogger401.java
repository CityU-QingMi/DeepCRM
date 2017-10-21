    @Override
    public boolean equals(Object other) {
        
        if (other == null) {
            return false;
        }
        
        if (other instanceof WeblogCategory) {
            WeblogCategory o = (WeblogCategory) other;
            return new EqualsBuilder()
                .append(getName(), o.getName())
                .append(getWeblog(), o.getWeblog())
                .isEquals();
        }        
        return false;
    }
