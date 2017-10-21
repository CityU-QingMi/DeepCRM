    public void reset(String sql) {

        sqlString            = sql;
        currentPosition      = 0;
        tokenPosition        = 0;
        limit                = sqlString.length();
        hasNonSpaceSeparator = false;
        eolPosition          = -1;
        lineNumber           = 1;

        token.reset();

        token.tokenType = Tokens.X_STARTPARSE;
    }
