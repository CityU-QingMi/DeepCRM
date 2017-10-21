    protected String describe(Session session, int blanks) {

        StringBuffer sb = new StringBuffer();

        sb.append(getLeftNode().describe(session, blanks));

        if (isDescending) {
            for (int i = 0; i < blanks; i++) {
                sb.append(' ');
            }

            sb.append(Tokens.T_DESC).append('\n');
        }

        return sb.toString();
    }
