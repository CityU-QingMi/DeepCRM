    ClobData createClobFromFile(String filename, String encoding) {

        File        file       = getFile(filename);
        long        fileLength = file.length();
        InputStream is         = null;

        try {
            ClobData clob = session.createClob(fileLength);

            is = new FileInputStream(file);

            Reader reader = new InputStreamReader(is, encoding);

            allocateClobSegments(clob.getId(), 0, reader);

            return clob;
        } catch (IOException e) {
            throw Error.error(ErrorCode.FILE_IO_ERROR, e.toString());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {}
        }
    }
