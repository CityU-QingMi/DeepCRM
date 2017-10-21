    Result getResultRecursive(Session session) {

        Result tempResult;

        recursiveTable.materialise(session);

        RowSetNavigatorData recNav = recursiveTable.getNavigator(session);
        Result              result = Result.newResult(recNav);

        result.metaData = resultMetaData;

        for (int round = 0; ; round++) {
            tempResult = rightQueryExpression.getResult(session, 0);

            RowSetNavigatorData tempNavigator =
                (RowSetNavigatorData) tempResult.getNavigator();

            if (tempNavigator.isEmpty()) {
                break;
            }

            int startSize = recNav.getSize();

            switch (unionType) {

                case UNION :
                    recNav.union(session, tempNavigator);
                    break;

                case UNION_ALL :
                    recNav.unionAll(session, tempNavigator);
                    break;

                default :
                    throw Error.runtimeError(ErrorCode.U_S0500,
                                             "QueryExpression");
            }

            if (startSize == recNav.getSize()) {
                break;
            }

            if (round > 256) {
                throw Error.error(ErrorCode.X_22522);
            }
        }

        return result;
    }
