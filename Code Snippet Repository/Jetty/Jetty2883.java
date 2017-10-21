    @Deprecated
    public ByteBuffer getResponses(ByteBuffer requestsBuffer,long idleFor,TimeUnit units) throws Exception
    {
        if (LOG.isDebugEnabled())
            LOG.debug("requests {}", BufferUtil.toUTF8String(requestsBuffer));
        LocalEndPoint endp = executeRequest(requestsBuffer);
        endp.waitUntilClosedOrIdleFor(idleFor,units);
        ByteBuffer responses = endp.takeOutput();
        if (endp.isOutputShutdown())
            endp.close();
        if (LOG.isDebugEnabled())
            LOG.debug("responses {}", BufferUtil.toUTF8String(responses));
        return responses;
    }
