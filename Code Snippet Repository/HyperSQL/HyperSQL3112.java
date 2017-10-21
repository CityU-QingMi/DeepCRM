    protected Result read() throws IOException, HsqlException {

        dataInput = new DataInputStream(
            new BufferedInputStream(httpConnection.getInputStream()));

        rowOut.reset();

        Result result = Result.newResult(dataInput, rowIn);

        result.readAdditionalResults(this, dataInput, rowIn);
        dataInput.close();    // Added to ensure connection is returned to Java

        // engine for transparent re-use of Keep-alive
        // connections (Aart)
        return result;
    }
