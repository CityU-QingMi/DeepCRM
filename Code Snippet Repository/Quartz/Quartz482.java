    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StringMatcher<?> other = (StringMatcher<?>) obj;
        if (compareTo == null) {
            if (other.compareTo != null)
                return false;
        } else if (!compareTo.equals(other.compareTo))
            return false;
        if (compareWith == null) {
            if (other.compareWith != null)
                return false;
        } else if (!compareWith.equals(other.compareWith))
            return false;
        return true;
    }
