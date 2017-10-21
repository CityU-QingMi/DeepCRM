    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("isAnd=").append(isAnd);
        if (map.size() > 0) {
            sb.append(", {");
            for (int i = 0; i < map.size(); i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                final List<String> list = map.getValueAt(i);
                final String value = list.size() > 1 ? list.get(0) : list.toString();
                sb.append(map.getKeyAt(i)).append('=').append(value);
            }
            sb.append('}');
        }
        return sb.toString();
    }
