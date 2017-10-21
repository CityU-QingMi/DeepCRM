    public TableDerived newDerivedTable(Session session) {

        TableDerived td;
        ParserDQL p = new ParserDQL(session, new Scanner(),
                                    session.parser.compileContext);

        p.reset(session, statement);
        p.read();

        td = p.XreadViewSubqueryTable(this, false);

        return td;
    }
