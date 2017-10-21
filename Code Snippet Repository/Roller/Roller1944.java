    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof GlobalPermission)) {
            return false;
        }
        GlobalPermission o = (GlobalPermission) other;
        return new EqualsBuilder()
                .append(getActions(), o.getActions())
                .isEquals();
    }
