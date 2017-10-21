    public boolean readLoggedStatement(Session session) {

        if (!sessionChanged) {
            try {
                rawStatement = dataStreamIn.readLine();
            } catch (EOFException e) {
                return false;
            } catch (IOException e) {
                throw Error.error(e, ErrorCode.FILE_IO_ERROR, null);
            }

            lineCount++;

            //        System.out.println(lineCount);
            statement = StringConverter.unicodeStringToString(rawStatement);

            if (statement == null) {
                return false;
            }
        }

        processStatement(session);

        return true;
    }
