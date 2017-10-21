    private void writeHeader(String header) {

        try {
            byte[] buf       = null;
            String firstLine = header + TextFileSettings.NL;

            try {
                buf = firstLine.getBytes(textFileSettings.charEncoding);
            } catch (UnsupportedEncodingException e) {
                buf = firstLine.getBytes();
            }

            dataFile.seek(0);
            dataFile.write(buf, 0, buf.length);

            fileFreePosition = buf.length;
        } catch (Throwable t) {
            throw Error.error(ErrorCode.TEXT_FILE_IO, t);
        }
    }
