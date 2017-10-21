    QuerySpecification XreadSimpleTable() {

        QuerySpecification select;

        switch (token.tokenType) {

            case Tokens.TABLE : {
                read();

                Table table = readNamedSubqueryOrNull();

                if (table == null) {
                    table = readTableName(true);
                }

                if (table.isView()) {
                    table = ((View) table).newDerivedTable(session);
                }

                select = new QuerySpecification(session, table,
                                                compileContext, false);

                break;
            }
            case Tokens.VALUES : {
                read();

                TableDerived td = XreadRowValueExpressionList();

                select = new QuerySpecification(session, td, compileContext,
                                                true);

                break;
            }
            case Tokens.SELECT : {
                select = XreadQuerySpecification();

                break;
            }
            default : {
                throw unexpectedToken();
            }
        }

        return select;
    }
