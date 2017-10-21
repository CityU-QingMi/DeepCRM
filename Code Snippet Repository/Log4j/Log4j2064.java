        @Override
        public URLStreamHandler createURLStreamHandler(final String protocol) {
            return new URLStreamHandler() {
                @Override
                protected URLConnection openConnection(final URL url) {
                    return open(url, null);
                }

                private URLConnection open(final URL url, final Proxy proxy) {
                    return new URLConnection(url) {
                        @Override
                        public void connect() throws IOException {
                            // do nothing
                        }
                    };
                }

                @Override
                protected URLConnection openConnection(final URL url, final Proxy proxy) {
                    return open(url, proxy);
                }

                @Override
                protected int getDefaultPort() {
                    return 1;
                }
            };
        }
