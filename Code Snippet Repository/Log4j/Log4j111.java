    private static boolean appendSpecialTypes(final Object o, final StringBuilder str) {
        if (o == null || o instanceof String) {
            str.append((String) o);
            return true;
        } else if (o instanceof CharSequence) {
            str.append((CharSequence) o);
            return true;
        } else if (o instanceof StringBuilderFormattable) {
            ((StringBuilderFormattable) o).formatTo(str);
            return true;
        } else if (o instanceof Integer) { // LOG4J2-1415 unbox auto-boxed primitives to avoid calling toString()
            str.append(((Integer) o).intValue());
            return true;
        } else if (o instanceof Long) {
            str.append(((Long) o).longValue());
            return true;
        } else if (o instanceof Double) {
            str.append(((Double) o).doubleValue());
            return true;
        } else if (o instanceof Boolean) {
            str.append(((Boolean) o).booleanValue());
            return true;
        } else if (o instanceof Character) {
            str.append(((Character) o).charValue());
            return true;
        } else if (o instanceof Short) {
            str.append(((Short) o).shortValue());
            return true;
        } else if (o instanceof Float) {
            str.append(((Float) o).floatValue());
            return true;
        }
        return appendDate(o, str);
    }
