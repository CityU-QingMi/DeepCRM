    public static Result newErrorResult(Throwable t, String statement) {

        Result result = newResult(ResultConstants.ERROR);

        if (t instanceof HsqlException) {
            result.exception  = (HsqlException) t;
            result.mainString = result.exception.getMessage();
            result.subString  = result.exception.getSQLState();

            if (statement != null) {
                result.mainString += " in statement [" + statement + "]";
            }

            result.errorCode = result.exception.getErrorCode();
        } else if (t instanceof OutOfMemoryError) {

            // gc() at this point may clear the memory allocated so far

            /** @todo 1.9.0 - review if it's better to gc higher up the stack */
            System.gc();

            result.exception  = Error.error(ErrorCode.OUT_OF_MEMORY, t);
            result.mainString = result.exception.getMessage();
            result.subString  = result.exception.getSQLState();
            result.errorCode  = result.exception.getErrorCode();
        } else if (t instanceof Throwable) {
            result.exception  = Error.error(ErrorCode.GENERAL_ERROR, t);
            result.mainString = result.exception.getMessage();
            result.subString  = result.exception.getSQLState();
            result.errorCode  = result.exception.getErrorCode();
        } else {
            result.exception  = Error.error(ErrorCode.GENERAL_ERROR);
            result.mainString = result.exception.getMessage();
            result.subString  = result.exception.getSQLState();
            result.errorCode  = result.exception.getErrorCode();
        }

        return result;
    }
