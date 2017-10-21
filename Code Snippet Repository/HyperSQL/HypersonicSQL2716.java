        public int read() throws IOException {

            if (!initialised) {
                initialise();
            }

            if (fetchedSize == limitSize) {
                return -1;
            }

            int byteread = is.read();

            if (byteread < 0) {
                throw new IOException("backup file not complete "
                                      + fetchedSize + " " + limitSize);
            }

            fetchedSize++;

            return byteread;
        }
