        @Override
        public Stream.Listener onNewStream(Stream stream, HeadersFrame frame)
        {
            if (LOGGER.isDebugEnabled())
                LOGGER.debug("Received {} for {} on {}: {}", frame, stream, stream.getSession(), frame.getMetaData());
            // Forward to the right server.
            MetaData metaData = frame.getMetaData();
            HttpFields fields = metaData.getFields();
            int port = Integer.parseInt(fields.get("X-Target"));
            ClientToProxyToServer clientToProxyToServer = forwarders.computeIfAbsent(port, p -> new ClientToProxyToServer("localhost", p, client));
            clientToProxyToServer.offer(stream, frame, Callback.NOOP);
            return clientToProxyToServer;
        }
