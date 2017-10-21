        @Override
        public void onStart(int request, FCGI.Role role, int flags)
        {
            // TODO: handle flags
            HttpChannelOverFCGI channel = new HttpChannelOverFCGI(connector, configuration, getEndPoint(),
                    new HttpTransportOverFCGI(connector.getByteBufferPool(), flusher, request, sendStatus200));
            HttpChannelOverFCGI existing = channels.putIfAbsent(request, channel);
            if (existing != null)
                throw new IllegalStateException();
            if (LOG.isDebugEnabled())
                LOG.debug("Request {} start on {}", request, channel);
        }
