    protected String describe(Session session, int blanks) {

        StringBuffer sb = new StringBuffer(64);

        sb.append('\n');

        for (int i = 0; i < blanks; i++) {
            sb.append(' ');
        }

        switch (opType) {

            case OpTypes.ARRAY_AGG :
                sb.append(Tokens.T_ARRAY_AGG).append(' ');
                break;

            case OpTypes.GROUP_CONCAT :
                sb.append(Tokens.T_GROUP_CONCAT).append(' ');
                break;

            case OpTypes.MEDIAN :
                sb.append(Tokens.T_MEDIAN).append(' ');
                break;

            default :
        }

        if (getLeftNode() != null) {
            sb.append(" arg=[");
            sb.append(nodes[LEFT].describe(session, blanks + 1));
            sb.append(']');
        }

        return sb.toString();
    }
