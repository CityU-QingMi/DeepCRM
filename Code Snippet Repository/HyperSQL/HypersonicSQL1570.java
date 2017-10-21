    private int onStartEscapeSequence(String sql, StringBuffer sb,
                                      int i) throws SQLException {

        sb.append(' ');

        i++;

        i = StringUtil.skipSpaces(sql, i);

        if (sql.regionMatches(true, i, "fn ", 0, 3)
                || sql.regionMatches(true, i, "oj ", 0, 3)) {
            i += 2;
        } else if (sql.regionMatches(true, i, "ts ", 0, 3)) {
            sb.append(Tokens.T_TIMESTAMP);

            i += 2;
        } else if (sql.regionMatches(true, i, "d ", 0, 2)) {
            sb.append(Tokens.T_DATE);

            i++;
        } else if (sql.regionMatches(true, i, "t ", 0, 2)) {
            sb.append(Tokens.T_TIME);

            i++;
        } else if (sql.regionMatches(true, i, "call ", 0, 5)) {
            sb.append(Tokens.T_CALL);

            i += 4;
        } else if (sql.regionMatches(true, i, "?= call ", 0, 8)) {
            sb.append(Tokens.T_CALL);

            i += 7;
        } else if (sql.regionMatches(true, i, "? = call ", 0, 8)) {
            sb.append(Tokens.T_CALL);

            i += 8;
        } else if (sql.regionMatches(true, i, "escape ", 0, 7)) {
            i += 6;
        } else {
            i--;

            throw JDBCUtil.sqlException(
                Error.error(
                    ErrorCode.JDBC_CONNECTION_NATIVE_SQL, sql.substring(i)));
        }

        return i;
    }
