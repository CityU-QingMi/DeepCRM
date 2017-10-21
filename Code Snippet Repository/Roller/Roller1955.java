    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof MediaFileDirectory)) {
            return false;
        }
        MediaFileDirectory o = (MediaFileDirectory) other;
        return new EqualsBuilder().append(getId(), o.getId())
                .append(getName(), o.getName())
                .append(getDescription(), o.getDescription()).isEquals();
    }
