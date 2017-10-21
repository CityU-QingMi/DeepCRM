    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof MapEntry)) {
            return false;
        }
        final MapEntry other = (MapEntry) obj;
        if (this.getKey() == null) {
            if (other.getKey() != null) {
                return false;
            }
        } else if (!this.getKey().equals(other.getKey())) {
            return false;
        }
        if (this.getValue() == null) {
            if (other.getValue() != null) {
                return false;
            }
        } else if (!this.getValue().equals(other.getValue())) {
            return false;
        }
        return true;
    }
