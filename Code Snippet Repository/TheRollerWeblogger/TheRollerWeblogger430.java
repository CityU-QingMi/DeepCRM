    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof WeblogPermission)) {
            return false;
        }
        WeblogPermission o = (WeblogPermission)other;
        return new EqualsBuilder()
                .append(getUserName(), o.getUserName())
                .append(getObjectId(), o.getObjectId())
                .append(getActions(), o.getActions())
                .isEquals();
    }
