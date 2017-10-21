    protected void freeOutputStream() {

        if (this.outputStream != null) {
            try {
                this.outputStream.free();
            } catch (IOException ex) {

                // ex.printStackTrace();
            }
            this.outputStream = null;
        }
    }
