    public boolean readLoggedStatement(Session session) {

        if (dataInput == null) {
            return super.readLoggedStatement(session);
        }

        int count;

        try {
            count = dataInput.readInt();

            if (count * 2 > buffer.length) {
                buffer = new byte[count * 2];
            }

            dataInput.readFully(buffer, 0, count);
        } catch (Throwable t) {
            return false;
        }

        count = crypto.decode(buffer, 0, count, buffer, 0);

        String s;

        try {
            s = new String(buffer, 0, count, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw Error.error(e, ErrorCode.FILE_IO_ERROR, fileNamePath);
        }

        lineCount++;

//        System.out.println(lineCount);
        statement = StringConverter.unicodeStringToString(s);

        if (statement == null) {
            return false;
        }

        processStatement(session);

        return true;
    }
