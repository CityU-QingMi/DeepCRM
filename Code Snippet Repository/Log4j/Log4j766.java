    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("mode=").append(mode);
        sb.append(" {");
        boolean first = true;
        for (final Map.Entry<String, Object> entry : map.entrySet()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(entry.getKey()).append('=').append(entry.getValue());
            first = false;
        }
        sb.append('}');
        return sb.toString();
    }
