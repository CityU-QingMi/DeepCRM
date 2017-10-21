    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filters.length; i++) {
            if (sb.length() == 0) {
                sb.append('{');
            } else {
                sb.append(", ");
            }
            sb.append(filters[i].toString());
        }
        if (sb.length() > 0) {
            sb.append('}');
        }
        return sb.toString();
    }
