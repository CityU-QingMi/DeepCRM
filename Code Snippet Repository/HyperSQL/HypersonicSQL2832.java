    public static Result newSessionAttributesResult() {

        Result result = newResult(ResultConstants.DATA);

        result.navigator = new RowSetNavigatorClient(1);
        result.metaData  = sessionAttributesMetaData;

        result.navigator.add(new Object[SessionInterface.INFO_LIMIT]);

        return result;
    }
