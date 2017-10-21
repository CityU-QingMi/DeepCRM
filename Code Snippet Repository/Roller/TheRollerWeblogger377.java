    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserRole)) {
            return false;
        }
        UserRole o = (UserRole)other;
        return new EqualsBuilder()
        .append(getRole(), o.getRole())
        .append(getUserName(), o.getUserName())
        .isEquals();
    }
