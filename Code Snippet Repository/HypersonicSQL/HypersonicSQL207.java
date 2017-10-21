    protected String describe(Session session, int blanks) {

        StringBuffer sb = new StringBuffer(64);

        sb.append('\n');

        for (int i = 0; i < blanks; i++) {
            sb.append(' ');
        }

        if (isTable) {
            sb.append(Tokens.T_TABLE).append(' ');
        } else {
            sb.append(Tokens.T_UNNEST).append(' ');
        }

        sb.append(nodes[LEFT].describe(session, blanks));

        return sb.toString();
    }
