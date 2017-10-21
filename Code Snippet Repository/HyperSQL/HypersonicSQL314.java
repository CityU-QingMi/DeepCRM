    private Statement compileDisconnect() {

        read();

        String sql = Tokens.T_DISCONNECT;
        Statement cs = new StatementSession(StatementTypes.DISCONNECT,
                                            (Object[]) null);

        return cs;
    }
