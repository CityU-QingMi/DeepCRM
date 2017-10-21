        public InputStream openInputStreamElement(final String fileName)
        throws IOException {

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

            return fis;
        }
