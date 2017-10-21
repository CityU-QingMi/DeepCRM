    protected String describe(Session session, int blanks) {

        if (likeObject == null) {
            return super.describe(session, blanks);
        }

        StringBuffer sb = new StringBuffer();

        sb.append('\n');

        for (int i = 0; i < blanks; i++) {
            sb.append(' ');
        }

        sb.append("LIKE ");
        sb.append(likeObject.describe(session));

        return sb.toString();
    }
