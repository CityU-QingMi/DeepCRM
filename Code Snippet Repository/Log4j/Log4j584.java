    protected boolean equalsImpl(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LifeCycle other = (LifeCycle) obj;
        if (state != other.getState()) {
            return false;
        }
        return true;
    }
