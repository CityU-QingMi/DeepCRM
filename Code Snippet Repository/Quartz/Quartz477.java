    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KeyMatcher<?> other = (KeyMatcher<?>) obj;
        if (compareTo == null) {
            if (other.compareTo != null)
                return false;
        } else if (!compareTo.equals(other.compareTo))
            return false;
        return true;
    }
