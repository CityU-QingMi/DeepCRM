    private void commit(MetaData.Response info, boolean head, ByteBuffer content, boolean lastContent, Callback callback)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("commit {} {} l={}",this,info,lastContent);
        boolean shutdown = this.shutdown = info.getFields().contains(HttpHeader.CONNECTION, HttpHeaderValue.CLOSE.asString());

        if (head)
        {
            if (lastContent)
            {
                Generator.Result headersResult = generateResponseHeaders(info, Callback.NOOP);
                Generator.Result contentResult = generateResponseContent(BufferUtil.EMPTY_BUFFER, true, callback);
                flusher.flush(headersResult, contentResult);
            }
            else
            {
                Generator.Result headersResult = generateResponseHeaders(info, callback);
                flusher.flush(headersResult);
            }
        }
        else
        {
            Generator.Result headersResult = generateResponseHeaders(info, Callback.NOOP);
            Generator.Result contentResult = generateResponseContent(content, lastContent, callback);
            flusher.flush(headersResult, contentResult);
        }

        if (lastContent && shutdown)
            flusher.shutdown();
    }
