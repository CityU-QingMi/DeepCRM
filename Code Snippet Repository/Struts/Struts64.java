    public String execute() {
        // get service
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errByteStream = new ByteArrayOutputStream();
        PrintStream outStream = new PrintStream(outByteStream);
        PrintStream errStream = new PrintStream(errByteStream);

        String outString = null;
        String errString = null;
        try {
            executeCommand(command, outStream, errStream);
            outString = outByteStream.toString().trim();
            errString = errByteStream.toString().trim();
        } catch (Exception e) {
            errString = e.getMessage();
        } finally {
            outStream.close();
            errStream.close();
        }

        output = errString != null && errString.length() > 0 ? errString : outString;
        return Action.SUCCESS;
    }
