        void reconnect() throws IOException {
            final Socket sock = createSocket(inetAddress.getHostName(), port);
            @SuppressWarnings("resource") // newOS is managed by the enclosing Manager.
            final OutputStream newOS = sock.getOutputStream();
            synchronized (owner) {
                Closer.closeSilently(getOutputStream());
                setOutputStream(newOS);
                socket = sock;
                reconnector = null;
                shutdown = true;
            }
            LOGGER.debug("Connection to {}:{} reestablished: {}", host, port, socket);
        }
