        @Override
        public void handshakeSucceeded(Event event)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("handshakeSucceeded {} {}", alpnConnection, event);
            if (alpnConnection.getProtocol()==null)
            {
                LOG.warn("No ALPN callback! {} {}",alpnConnection, event);
                alpnConnection.unsupported();
            }
        }
