        @Override
        public Connection newConnection(SelectableChannel channel, EndPoint endpoint, Object attachment)
        {
            SSLEngine engine = __sslCtxFactory.newSSLEngine();
            engine.setUseClientMode(false);
            SslConnection sslConnection = new SslConnection(__byteBufferPool, getExecutor(), endpoint, engine);
            sslConnection.setRenegotiationAllowed(__sslCtxFactory.isRenegotiationAllowed());
            sslConnection.setRenegotiationLimit(__sslCtxFactory.getRenegotiationLimit());
            Connection appConnection = new TestConnection(sslConnection.getDecryptedEndPoint());
            sslConnection.getDecryptedEndPoint().setConnection(appConnection);
            return sslConnection;
        }
