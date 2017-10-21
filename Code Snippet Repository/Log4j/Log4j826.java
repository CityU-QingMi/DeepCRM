    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Duration)) {
            return false;
        }
        final Duration other = (Duration) obj;
        return other.seconds == this.seconds;
    }
