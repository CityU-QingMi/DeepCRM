    static String getSQL(Token[] tokens) {

        boolean      wasDelimiter = true;
        StringBuffer sb           = new StringBuffer();

        for (int i = 0; i < tokens.length; i++) {
            String sql = tokens[i].getSQL();

            if (!tokens[i].isDelimiter && !wasDelimiter) {
                sb.append(' ');
            }

            sb.append(sql);

            wasDelimiter = tokens[i].isDelimiter;
        }

        return sb.toString();
    }
