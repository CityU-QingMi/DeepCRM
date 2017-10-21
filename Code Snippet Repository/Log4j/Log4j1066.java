    @Override
    protected boolean equalsImpl(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equalsImpl(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractFilter other = (AbstractFilter) obj;
        if (onMatch != other.onMatch) {
            return false;
        }
        if (onMismatch != other.onMismatch) {
            return false;
        }
        return true;
    }
