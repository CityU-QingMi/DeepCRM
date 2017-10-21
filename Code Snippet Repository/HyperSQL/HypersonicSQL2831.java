    public static Result newDataRowsResult(Result source, int offset,
                                           int count) {

        if (offset + count > source.navigator.getSize()) {
            count = source.navigator.getSize() - offset;
        }

        Result result = newResult(ResultConstants.DATAROWS);

        result.id       = source.id;
        result.metaData = source.metaData;
        result.navigator = new RowSetNavigatorClient(source.navigator, offset,
                count);

        return result;
    }
