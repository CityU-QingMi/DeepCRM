    public static Result newDataHeadResult(SessionInterface session,
                                           Result source, int offset,
                                           int count) {

        if (offset + count > source.navigator.getSize()) {
            count = source.navigator.getSize() - offset;
        }

        Result result = newResult(ResultConstants.DATAHEAD);

        result.metaData = source.metaData;
        result.navigator = new RowSetNavigatorClient(source.navigator, offset,
                count);

        result.navigator.setId(source.navigator.getId());
        result.setSession(session);

        result.rsProperties = source.rsProperties;
        result.fetchSize    = source.fetchSize;

        return result;
    }
