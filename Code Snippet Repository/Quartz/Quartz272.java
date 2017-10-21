        @Override
        public void close() throws IOException {
            try {
                delegate.close();
            } finally {
                try {
                    source.free();
                } catch (SQLException ex) {
                    throw new IOException(ex);
                }
            }
        }
