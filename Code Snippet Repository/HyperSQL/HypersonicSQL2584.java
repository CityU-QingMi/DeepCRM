    private void resetStream() throws IOException {

        if (dataInput != null) {
            dataInput.close();
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

        dataInput    = new DataInputStream(fis);
        realPosition = 0;
    }
