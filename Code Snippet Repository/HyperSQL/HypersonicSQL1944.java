        public boolean isStreamElement(String fileName) {

            URL url = null;

            try {
                url = getClass().getResource(fileName);

                if (url == null) {
                    ClassLoader cl =
                        Thread.currentThread().getContextClassLoader();

                    if (cl != null) {
                        url = cl.getResource(fileName);
                    }
                }
            } catch (Throwable t) {

                //
            }

            return url != null;
        }
