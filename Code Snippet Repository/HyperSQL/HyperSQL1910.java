        public int read(byte[] bytes, int offset,
                        int length) throws IOException {

            if (!initialised) {
                initialise();
            }

            if (fetchedSize == limitSize) {
                return -1;
            }

            if (limitSize >= 0 && limitSize - fetchedSize < length) {
                length = (int) (limitSize - fetchedSize);
            }

            int count = is.read(bytes, offset, length);

            if (count < 0) {
                throw new IOException("backup file not complete "
                                      + fetchedSize + " " + limitSize);
            }

            fetchedSize += count;

            return count;
        }
