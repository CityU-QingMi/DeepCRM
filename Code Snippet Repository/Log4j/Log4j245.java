    public static void appendValue(final StringBuilder stringBuilder, final Object obj) {
        if (obj == null || obj instanceof String) {
            stringBuilder.append((String) obj);
        } else if (obj instanceof StringBuilderFormattable) {
            ((StringBuilderFormattable) obj).formatTo(stringBuilder);
        } else if (obj instanceof CharSequence) {
            stringBuilder.append((CharSequence) obj);
        } else if (obj instanceof Integer) { // LOG4J2-1437 unbox auto-boxed primitives to avoid calling toString()
            stringBuilder.append(((Integer) obj).intValue());
        } else if (obj instanceof Long) {
            stringBuilder.append(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            stringBuilder.append(((Double) obj).doubleValue());
        } else if (obj instanceof Boolean) {
            stringBuilder.append(((Boolean) obj).booleanValue());
        } else if (obj instanceof Character) {
            stringBuilder.append(((Character) obj).charValue());
        } else if (obj instanceof Short) {
            stringBuilder.append(((Short) obj).shortValue());
        } else if (obj instanceof Float) {
            stringBuilder.append(((Float) obj).floatValue());
        } else {
            stringBuilder.append(obj);
        }
    }
