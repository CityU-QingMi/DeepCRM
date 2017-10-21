    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof PingTarget)) {
            return false;
        }
        PingTarget o = (PingTarget)other;
        return new EqualsBuilder()
            .append(getId(), o.getId()) 
            .isEquals();
    }
