    @Override
    public void formatTo(final StringBuilder buffer) {
        if (obj == null || obj instanceof String) {
            buffer.append((String) obj);
        } else if (obj instanceof StringBuilderFormattable) {
            ((StringBuilderFormattable) obj).formatTo(buffer);
        } else if (obj instanceof CharSequence) {
            buffer.append((CharSequence) obj);
        } else if (obj instanceof Integer) { // LOG4J2-1437 unbox auto-boxed primitives to avoid calling toString()
            buffer.append(((Integer) obj).intValue());
        } else if (obj instanceof Long) {
            buffer.append(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            buffer.append(((Double) obj).doubleValue());
        } else if (obj instanceof Boolean) {
            buffer.append(((Boolean) obj).booleanValue());
        } else if (obj instanceof Character) {
            buffer.append(((Character) obj).charValue());
        } else if (obj instanceof Short) {
            buffer.append(((Short) obj).shortValue());
        } else if (obj instanceof Float) {
            buffer.append(((Float) obj).floatValue());
        } else {
            buffer.append(obj);
        }
    }
