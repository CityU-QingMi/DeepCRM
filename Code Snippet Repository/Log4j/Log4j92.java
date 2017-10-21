    protected void asJson(final StringBuilder sb) {
        sb.append('{');
        for (int i = 0; i < data.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            StringBuilders.appendDqValue(sb, data.getKeyAt(i)).append(':');
            StringBuilders.appendDqValue(sb, data.getValueAt(i));
        }
        sb.append('}');
    }
