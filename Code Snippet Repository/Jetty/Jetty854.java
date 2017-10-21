    public Result generateResponseContent(int request, ByteBuffer content, boolean lastContent, boolean aborted, Callback callback)
    {
        if (aborted)
        {
            Result result = new Result(byteBufferPool, callback);
            if (lastContent)
                result.append(generateEndRequest(request, true), true);
            else
                result.append(BufferUtil.EMPTY_BUFFER, false);
            return result;
        }
        else
        {
            Result result = generateContent(request, content, false, lastContent, callback, FCGI.FrameType.STDOUT);
            if (lastContent)
                result.append(generateEndRequest(request, false), true);
            return result;
        }
    }
