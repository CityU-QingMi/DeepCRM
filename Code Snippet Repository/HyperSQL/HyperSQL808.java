    protected void freeInputStream() {

        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            } catch (IOException ex) {

                // ex.printStackTrace();
            } finally {
                this.inputStream = null;
            }
        }
    }
