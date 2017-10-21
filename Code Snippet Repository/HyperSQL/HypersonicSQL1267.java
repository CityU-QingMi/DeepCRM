    private String formatNicely(Map<?, ?> map, boolean withValues) {
        String       s;
        StringBuffer sb = new StringBuffer();

        if (withValues) {
            SqlFile.appendLine(sb, SqltoolRB.pl_list_parens.getString());
        } else {
            SqlFile.appendLine(sb, SqltoolRB.pl_list_lengths.getString());
        }

        for (Map.Entry<Object, Object> entry
                : new TreeMap<Object, Object>(map).entrySet()) {
            s = (String) entry.getValue();

            SqlFile.appendLine(sb, "    " + (String) entry.getKey()
                    + ": " + (withValues ? ("(" + s + ')')
                    : Integer.toString( s.length())));
        }

        return sb.toString();
    }
