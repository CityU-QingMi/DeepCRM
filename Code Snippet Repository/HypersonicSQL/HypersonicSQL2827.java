    public static Result newResult(Session session, int mode,
                                   DataInput dataInput,
                                   RowInputBinary in) throws IOException {

        try {
            if (mode == ResultConstants.LARGE_OBJECT_OP) {
                return ResultLob.newLob(dataInput, false);
            }

            Result result = newResult(session, dataInput, in, mode);

            return result;
        } catch (IOException e) {
            throw Error.error(ErrorCode.X_08000);
        }
    }
