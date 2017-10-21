    @Override
	public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ReadOnlyStringMap)) {
            return false;
        }
        final ReadOnlyStringMap other = (ReadOnlyStringMap) obj;
        if (other.size() != size()) {
            return false;
        }
        int pos = arraySize;
        if (containsNullKey) {
            if (!Objects.equals(getObjectValue(null), other.getValue(null))) {
                return false;
            }
        }
        --pos;
        final K myKeys[] = this.keys;
        for (; pos >= 0; pos--) {
            K k;
            if ((k = myKeys[pos]) != null) {
                if (!Objects.equals(values[pos], other.getValue((String) k))) {
                    return false;
                }
            }
        }
        return true;
    }
