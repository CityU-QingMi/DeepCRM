    private void clearRowImage(CachedObject row) {

        try {
            int length = row.getStorageSize();
            int count  = length - textFileSettings.bytesForLineEnd.length;

            rowOut.reset();

            HsqlByteArrayOutputStream out = rowOut.getOutputStream();

            for (; count > 0; count -= textFileSettings.bytesForSpace.length) {
                out.write(textFileSettings.bytesForSpace);
            }

            out.write(textFileSettings.bytesForLineEnd);
            dataFile.seek(row.getPos());
            dataFile.write(out.getBuffer(), 0, out.size());
        } catch (Throwable t) {
            throw Error.runtimeError(ErrorCode.U_S0500, t.getMessage());
        }
    }
