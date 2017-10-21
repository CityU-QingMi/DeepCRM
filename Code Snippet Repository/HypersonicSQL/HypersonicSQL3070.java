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
            if (cryptoStream != null) {
                cryptoStream.close();
            }
        } catch (Exception e) {}

        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception e) {}

        try {
            if (dataInput != null) {
                dataInput.close();
            }
        } catch (Exception e) {}
    }
