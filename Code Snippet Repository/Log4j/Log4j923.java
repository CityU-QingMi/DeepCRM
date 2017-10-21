    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AppenderControl)) {
            return false;
        }
        final AppenderControl other = (AppenderControl) obj;
        return Objects.equals(appenderName, other.appenderName);
    }
