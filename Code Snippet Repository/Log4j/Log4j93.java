    protected void asJava(final StringBuilder sb) {
        sb.append('{');
        for (int i = 0; i < data.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            StringBuilders.appendKeyDqValue(sb, data.getKeyAt(i), data.getValueAt(i));
        }
        sb.append('}');
    }
