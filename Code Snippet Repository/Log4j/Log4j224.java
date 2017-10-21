    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(256);
        sb.append('{');
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(keys[i]).append('=');
            sb.append(values[i] == this ? "(this map)" : values[i]);
        }
        sb.append('}');
        return sb.toString();
    }
