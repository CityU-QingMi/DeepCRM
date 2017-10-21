    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SortedArrayStringMap)) {
            return false;
        }
        final SortedArrayStringMap other = (SortedArrayStringMap) obj;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!Objects.equals(keys[i], other.keys[i])) {
                return false;
            }
            if (!Objects.equals(values[i], other.values[i])) {
                return false;
            }
        }
        return true;
    }
