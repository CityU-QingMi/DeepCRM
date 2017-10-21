        protected OutputStreamAdapter(final File file,
                                   final long pos)
                                   throws FileNotFoundException, IOException,
                                          IllegalArgumentException,
                                          NullPointerException,
                                          SecurityException {

            if (pos < 0) {
                throw new IllegalArgumentException("pos: " + pos);
            }

            m_randomAccessFile = new RandomAccessFile(file, "rw");

            boolean seekSucceeded = false;

            try {
                m_randomAccessFile.seek(pos);
                seekSucceeded = true;
            } finally {
                if (!seekSucceeded) {
                    closeSafely(m_randomAccessFile);
                }
            }
        }
