    protected Result read() throws IOException, HsqlException {

        Result result = Result.newResult(dataInput, rowIn);

        result.readAdditionalResults(this, dataInput, rowIn);
        rowOut.reset(mainBuffer);
        rowIn.resetRow(mainBuffer.length);

        return result;
    }
