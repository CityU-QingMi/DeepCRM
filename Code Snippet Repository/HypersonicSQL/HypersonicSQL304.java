    private Statement compileRollback() {

        boolean chain     = false;
        String  savepoint = null;

        read();

        if (token.tokenType == Tokens.WORK) {
            read();
        }

        if (token.tokenType == Tokens.TO) {
            read();
            readThis(Tokens.SAVEPOINT);
            checkIsSimpleName();

            savepoint = token.tokenString;

            read();

            Object[] args = new Object[]{ savepoint };
            Statement cs =
                new StatementSession(StatementTypes.ROLLBACK_SAVEPOINT, args);

            return cs;
        } else {
            if (token.tokenType == Tokens.AND) {
                read();

                if (token.tokenType == Tokens.NO) {
                    read();
                } else {
                    chain = true;
                }

                readThis(Tokens.CHAIN);
            }
        }

        String sql = chain ? StatementSession.rollbackAndChainStatement.sql
                           : StatementSession.rollbackNoChainStatement.sql;
        Statement st = new StatementSession(StatementTypes.ROLLBACK_WORK,
                                            new Object[]{
                                                Boolean.valueOf(chain) });

        st.setSQL(sql);

        return st;
    }
