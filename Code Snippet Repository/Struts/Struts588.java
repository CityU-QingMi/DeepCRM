        public FastByteArrayOutputStream getContent() throws IOException {
            //if we are using a writer, we need to flush the
            //data to the underlying outputstream.
            //most containers do this - but it seems Jetty 4.0.5 doesn't
            if (pagePrintWriter != null) {
                pagePrintWriter.flush();
            }

            return ((PageOutputStream) getOutputStream()).getBuffer();
        }
