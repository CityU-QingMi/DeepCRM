    protected Content produceNextContext() throws IOException
    {
        Content content = nextInterceptedContent();
        if (content == null && !isFinished())
        {
            produceContent();
            content = nextInterceptedContent();
        }
        return content;
    }
