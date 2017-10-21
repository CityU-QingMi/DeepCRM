    public static Result newSetSavepointRequest(String name) {

        Result result;

        result = newResult(ResultConstants.SETCONNECTATTR);

        result.setConnectionAttrType(ResultConstants.SQL_ATTR_SAVEPOINT_NAME);
        result.setMainString(name);

        return result;
    }
