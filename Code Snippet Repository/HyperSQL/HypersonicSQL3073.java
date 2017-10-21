    public void close() {

        try {
            if (dataStreamIn != null) {
                dataStreamIn.close();
            }
        } catch (Exception e) {}

        try {
            if (gzipStream != null) {
                gzipStream.close();
            }
        } catch (Exception e) {}

        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception e) {}

        try {
            if (scrwriter != null) {
                scrwriter.close();
            }

            database.recoveryMode = 0;
        } catch (Exception e) {}
    }
