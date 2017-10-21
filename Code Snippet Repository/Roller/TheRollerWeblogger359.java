    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof MediaFileTag)) {
            return false;
        }
        MediaFileTag o = (MediaFileTag) other;
        return new EqualsBuilder().append(getName(), o.getName())
                .append(getMediaFile(), o.getMediaFile()).isEquals();
    }
