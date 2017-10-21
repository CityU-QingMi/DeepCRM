        @Override
        public void handshakeSucceeded(Event event)
        {
            try
            {
                SSLEngine sslEngine = alpnConnection.getSSLEngine();
                Method method = sslEngine.getClass().getDeclaredMethod("getApplicationProtocol");
                method.setAccessible(true);
                String protocol = (String)method.invoke(sslEngine);
                alpnConnection.selected(protocol);
            }
            catch (Throwable e)
            {
                alpnConnection.selected(null);
                LOG.warn(e);
            }
        }
