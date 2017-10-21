    protected void write(Result r) throws IOException, HsqlException {

        HsqlByteArrayOutputStream memStream  = new HsqlByteArrayOutputStream();
        DataOutputStream          tempOutput = new DataOutputStream(memStream);

        r.write(this, tempOutput, rowOut);
        httpConnection.setRequestMethod("POST");
        httpConnection.setDoOutput(true);
        httpConnection.setUseCaches(false);

        //httpConnection.setRequestProperty("Accept-Encoding", "gzip");
        httpConnection.setRequestProperty("Content-Type",
                                          "application/octet-stream");
        httpConnection.setRequestProperty("Content-Length",
                                          String.valueOf(IDLENGTH
                                              + memStream.size()));

        dataOutput = new DataOutputStream(httpConnection.getOutputStream());

        dataOutput.writeInt(r.getDatabaseId());
        dataOutput.writeLong(r.getSessionId());
        memStream.writeTo(dataOutput);
        dataOutput.flush();
    }
