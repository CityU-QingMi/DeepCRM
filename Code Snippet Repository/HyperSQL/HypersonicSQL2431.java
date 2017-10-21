    private void readObject(long pos) {

        try {
            dataFile.seek(pos * dataFileScale);

            int size = dataFile.readInt();

            rowIn.resetRow(pos, size);
            dataFile.read(rowIn.getBuffer(), 4, size - 4);
        } catch (Throwable t) {
            logSevereEvent("DataFileCache.readObject", t, pos);

            HsqlException ex = Error.error(ErrorCode.DATA_FILE_ERROR, t);

            if (rowIn.getFilePosition() != pos) {
                rowIn.resetRow(pos, 0);
            }

            ex.info = rowIn;

            throw ex;
        }
    }
