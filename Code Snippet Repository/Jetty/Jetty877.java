    @Override
    public void send(MetaData.Response info, boolean head, ByteBuffer content, boolean lastContent, Callback callback)
    {
        if (info!=null)
            commit(info,head,content,lastContent,callback);
        else
        {
            if (head)
            {
                if (lastContent)
                {
                    Generator.Result result = generateResponseContent(BufferUtil.EMPTY_BUFFER, true, callback);
                    flusher.flush(result);
                }
                else
                {
                    // Skip content generation
                    callback.succeeded();
                }
            }
            else
            {
                Generator.Result result = generateResponseContent(content, lastContent, callback);
                flusher.flush(result);
            }

            if (lastContent && shutdown)
                flusher.shutdown();
        }
    }
