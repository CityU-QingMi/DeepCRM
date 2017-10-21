        protected WriterAdapter(final File file,
                final long pos) throws FileNotFoundException, IOException,
                SecurityException, NullPointerException,
                IllegalArgumentException {

            if (file == null) {
                throw new NullPointerException("file");
            }

            if (pos < 0) {
                throw new IllegalArgumentException("pos: " + pos);
            }

            ReaderAdapter reader = null;
            long filePointer;

            try {
                reader = new ReaderAdapter(file, pos, Long.MAX_VALUE);
                filePointer = reader.getFilePointer();
            } finally {
                closeSafely(reader);
            }

            RandomAccessFile raf = null;
            boolean success = false;
            try {
                raf = new RandomAccessFile(file, "rw");

                raf.seek(filePointer);
                success = true;
            } finally {
                if (!success) {
                    closeSafely(raf);
                }
            }

            final OutputStreamAdapter osa = new OutputStreamAdapter(raf);
            m_writer = m_encoding == null
                    ? new OutputStreamWriter(osa)
                    : new OutputStreamWriter(osa, m_charset);
        }
