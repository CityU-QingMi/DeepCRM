    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AutoPing)) {
            return false;
        }
        AutoPing o = (AutoPing)other;
        return new EqualsBuilder()
            .append(getId(), o.getId())
            .append(getPingTarget(), o.getPingTarget()) 
            .append(getWebsite(), o.getWebsite()) 
            .isEquals();
    }
