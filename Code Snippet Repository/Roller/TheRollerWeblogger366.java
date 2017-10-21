    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof RuntimeConfigProperty)) {
            return false;
        }
        RuntimeConfigProperty o = (RuntimeConfigProperty)other;
        return new EqualsBuilder()
        .append(getName(), o.getName())
        .isEquals();
    }
