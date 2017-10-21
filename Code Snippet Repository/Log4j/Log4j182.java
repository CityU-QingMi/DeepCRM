    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ThreadContextStack)) {
            return false;
        }
        final ThreadContextStack other = (ThreadContextStack) obj;
        final List<String> otherAsList = other.asList();
        if (this.list == null) {
            if (otherAsList != null) {
                return false;
            }
        } else if (!this.list.equals(otherAsList)) {
            return false;
        }
        return true;
    }
