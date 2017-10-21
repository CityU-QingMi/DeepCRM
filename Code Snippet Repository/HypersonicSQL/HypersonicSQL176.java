    public String getSQL() {

        if (likeObject == null) {
            return super.getSQL();
        }

        String       left  = getContextSQL(nodes[LEFT]);
        String       right = getContextSQL(nodes[RIGHT]);
        StringBuffer sb    = new StringBuffer();

        sb.append(left).append(' ').append(Tokens.T_LIKE).append(' ');
        sb.append(right);

        /** @todo fredt - scripting of non-ascii escapes needs changes to general script logging */
        if (nodes[ESCAPE] != null) {
            sb.append(' ').append(Tokens.T_ESCAPE).append(' ');
            sb.append(nodes[ESCAPE].getSQL());
            sb.append(' ');
        }

        return sb.toString();
    }
