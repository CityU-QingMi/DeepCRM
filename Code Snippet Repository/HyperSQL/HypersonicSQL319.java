    private Statement compileCommit() {

        boolean chain = false;

        read();
        readIfThis(Tokens.WORK);

        if (token.tokenType == Tokens.AND) {
            read();

            if (token.tokenType == Tokens.NO) {
                read();
            } else {
                chain = true;
            }

            readThis(Tokens.CHAIN);
        }

        String sql = chain ? StatementSession.commitAndChainStatement.sql
                           : StatementSession.commitNoChainStatement.sql;
        Statement st = new StatementSession(StatementTypes.COMMIT_WORK,
                                            new Object[]{
                                                Boolean.valueOf(chain) });

        st.setSQL(sql);

        return st;
    }
