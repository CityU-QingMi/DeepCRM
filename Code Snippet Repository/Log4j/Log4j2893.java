    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(256);
        sb.append('{');
        final K myKeys[] = this.keys;
        int pos = arraySize;
        boolean first = true;
        if (containsNullKey) {
            sb.append(myKeys[pos] == this ? "(this map)" : myKeys[pos]);
            sb.append('=');
            sb.append(values[pos] == this ? "(this map)" : values[pos]);
            first = false;
        }
        --pos;
        for (; pos >= 0; pos--) {
            if (myKeys[pos] != null) {
                if (first) {
                    first = false;
                } else {
                    sb.append(", ");
                }
                sb.append(myKeys[pos] == this ? "(this map)" : myKeys[pos]);
                sb.append('=');
                sb.append(values[pos] == this ? "(this map)" : values[pos]);
            }
        }
        sb.append('}');
        return sb.toString();
    }
