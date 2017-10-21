    public String getResponse(String rawRequest,long time,TimeUnit unit) throws Exception
    {
        boolean head = rawRequest.toLowerCase().startsWith("head ");
        ByteBuffer requestsBuffer = BufferUtil.toBuffer(rawRequest, StandardCharsets.ISO_8859_1);
        if (LOG.isDebugEnabled())
            LOG.debug("request {}", BufferUtil.toUTF8String(requestsBuffer));
        LocalEndPoint endp = executeRequest(requestsBuffer);
        
        return BufferUtil.toString(endp.waitForResponse(head,time,unit), StandardCharsets.ISO_8859_1);
    }
