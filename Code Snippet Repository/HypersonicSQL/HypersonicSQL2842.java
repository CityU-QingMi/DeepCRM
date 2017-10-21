    public static Result newPSMResult(int type, String label, Object value) {

        Result result = newResult(ResultConstants.VALUE);

        result.errorCode  = type;
        result.mainString = label;
        result.valueData  = value;

        return result;
    }
