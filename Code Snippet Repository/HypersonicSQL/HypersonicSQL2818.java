    public static Result newBatchedExecuteResponse(int[] updateCounts,
            Result generatedResult, Result e) {

        Result result = newResult(ResultConstants.BATCHEXECRESPONSE);

        result.addChainedResult(generatedResult);
        result.addChainedResult(e);

        Type[] types = new Type[]{ Type.SQL_INTEGER };

        result.metaData = ResultMetaData.newSimpleResultMetaData(types);

        Object[][] table = new Object[updateCounts.length][];

        for (int i = 0; i < updateCounts.length; i++) {
            table[i] = new Object[]{ ValuePool.getInt(updateCounts[i]) };
        }

        ((RowSetNavigatorClient) result.navigator).setData(table);

        return result;
    }
