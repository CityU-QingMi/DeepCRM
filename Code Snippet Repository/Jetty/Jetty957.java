        @Override
        public Connection newConnection(SelectableChannel channel, EndPoint endpoint, Object attachment) throws IOException
        {
            @SuppressWarnings("unchecked")
            Map<String, Object> context = (Map<String, Object>)attachment;
            context.put(HTTP2ClientConnectionFactory.BYTE_BUFFER_POOL_CONTEXT_KEY, getByteBufferPool());
            context.put(HTTP2ClientConnectionFactory.EXECUTOR_CONTEXT_KEY, getExecutor());
            context.put(HTTP2ClientConnectionFactory.SCHEDULER_CONTEXT_KEY, getScheduler());
            return getClientConnectionFactory().newConnection(endpoint, context);
        }
