        @Override
        public Connection newConnection(SelectableChannel channel, EndPoint endpoint, Object attachment) throws IOException
        {
            if (ConnectHandler.LOG.isDebugEnabled())
                ConnectHandler.LOG.debug("Connected to {}", ((SocketChannel)channel).getRemoteAddress());
            ConnectContext connectContext = (ConnectContext)attachment;
            UpstreamConnection connection = newUpstreamConnection(endpoint, connectContext);
            connection.setInputBufferSize(getBufferSize());
            return connection;
        }
