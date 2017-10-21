        @Override
        protected void connectionFailed(SelectableChannel channel, Throwable failure, Object attachment)
        {
            @SuppressWarnings("unchecked")
            Map<String, Object> context = (Map<String, Object>)attachment;
            if (LOG.isDebugEnabled())
            {
                Object host = context.get(SslClientConnectionFactory.SSL_PEER_HOST_CONTEXT_KEY);
                Object port = context.get(SslClientConnectionFactory.SSL_PEER_PORT_CONTEXT_KEY);
                LOG.debug("Could not connect to {}:{}", host, port);
            }
            @SuppressWarnings("unchecked")
            Promise<Session> promise = (Promise<Session>)context.get(HTTP2ClientConnectionFactory.SESSION_PROMISE_CONTEXT_KEY);
            promise.failed(failure);
        }
