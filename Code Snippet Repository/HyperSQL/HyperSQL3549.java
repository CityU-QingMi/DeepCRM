    void resolveTypesPartTwoRecursive(Session session) {

        for (int i = 0; i < unionColumnTypes.length; i++) {
            Type type = unionColumnTypes[i];

            exprColumns[i].setDataType(session, type);
        }

        setResultColumnTypes();
        createResultMetaData(session);
        createTable(session);
    }
