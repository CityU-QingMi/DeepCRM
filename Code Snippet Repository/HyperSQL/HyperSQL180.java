    public Result getResult(Session session) {

        switch (opType) {

            case OpTypes.ARRAY : {
                RowSetNavigatorData navigator = table.getNavigator(session);
                Object[]            array = new Object[navigator.getSize()];

                navigator.beforeFirst();

                for (int i = 0; navigator.next(); i++) {
                    Object[] data = navigator.getCurrent();

                    array[i] = data[0];
                }

                return Result.newPSMResult(array);
            }
            case OpTypes.TABLE_SUBQUERY : {
                table.materialiseCorrelated(session);

                RowSetNavigatorData navigator = table.getNavigator(session);
                Result              result    = Result.newResult(navigator);

                result.metaData = table.queryExpression.getMetaData();

                return result;
            }
            default : {
                Object value = getValue(session);

                return Result.newPSMResult(value);
            }
        }
    }
