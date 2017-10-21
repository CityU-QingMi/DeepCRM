        public void close() throws IOException {
            if (inputStream == null) {
                return;
            }

            try {
                inputStream.close();
            } finally {
                inputStream = null;    // Encourage buffer GC
            }
        }
