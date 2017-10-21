    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ExtendedStackTraceElement)) {
            return false;
        }
        final ExtendedStackTraceElement other = (ExtendedStackTraceElement) obj;
        if (this.extraClassInfo == null) {
            if (other.extraClassInfo != null) {
                return false;
            }
        } else if (!this.extraClassInfo.equals(other.extraClassInfo)) {
            return false;
        }
        if (this.stackTraceElement == null) {
            if (other.stackTraceElement != null) {
                return false;
            }
        } else if (!this.stackTraceElement.equals(other.stackTraceElement)) {
            return false;
        }
        return true;
    }
