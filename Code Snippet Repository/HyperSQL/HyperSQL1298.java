    private void setupWriter() {

        if (level == LOG_NONE) {
            close();

            return;
        }

        if (writer == null) {
            if (isSystem) {
                writer = new PrintWriter(System.out);
            } else {
                File file = new File(filePath);

                setupLog(file);
            }
        }
    }
