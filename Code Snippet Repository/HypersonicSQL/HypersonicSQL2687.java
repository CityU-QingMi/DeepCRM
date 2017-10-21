    private void resetStream() throws IOException {

        if (file != null) {
            file.close();
        }

        InputStream fis = null;

        try {
            fis = getClass().getResourceAsStream(fileName);

            if (fis == null) {
                ClassLoader cl =
                    Thread.currentThread().getContextClassLoader();

                if (cl != null) {
                    fis = cl.getResourceAsStream(fileName);
                }
            }
        } catch (Throwable t) {

            //
        } finally {
            if (fis == null) {
                throw new FileNotFoundException(fileName);
            }
        }

        file = new DataInputStream(fis);
    }
