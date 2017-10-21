        public void close() throws IOException {

            if (!this.closed) {
                this.closed = true;

                try {
                    this.writer.close();
                } catch (XMLStreamException e) {
                    throw new IOException(e);
                } finally {
                    this.writer     = null;
                    this.locator    = null;
                    this.namespaces = null;
                }
            }
        }
