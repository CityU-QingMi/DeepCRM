    protected String getExpressionSetSummary(final java.util.ArrayList<Integer> list) {

        if (list.contains(NO_SPEC)) {
            return "?";
        }
        if (list.contains(ALL_SPEC)) {
            return "*";
        }

        final StringBuilder buf = new StringBuilder();

        final Iterator<Integer> itr = list.iterator();
        boolean first = true;
        while (itr.hasNext()) {
            final Integer iVal = itr.next();
            final String val = iVal.toString();
            if (!first) {
                buf.append(",");
            }
            buf.append(val);
            first = false;
        }

        return buf.toString();
    }
