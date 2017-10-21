    void resolveTypesPartTwo(Session session) {

        if (isPartTwoResolved) {
            return;
        }

        resolveGroups();

        for (int i = 0; i < unionColumnTypes.length; i++) {
            Type type = unionColumnTypes[i];

            if (type == null) {
                if (session.database.sqlEnforceTypes) {
                    throw Error.error(ErrorCode.X_42567);
                }

                type                = Type.SQL_VARCHAR_DEFAULT;
                unionColumnTypes[i] = type;
            }

            exprColumns[i].setDataType(session, type);

            if (exprColumns[i].dataType.isArrayType()
                    && exprColumns[i].dataType.collectionBaseType() == null) {
                throw Error.error(ErrorCode.X_42567);
            }
        }

        for (int i = indexLimitVisible; i < indexStartHaving; i++) {
            if (exprColumns[i].dataType == null) {
                throw Error.error(ErrorCode.X_42567);
            }
        }

        checkLobUsage();
        setMergeability();
        setUpdatability();
        setResultColumnTypes();
        createResultMetaData(session);
        createTable(session);
        mergeQuery();

        isPartTwoResolved = true;
    }
