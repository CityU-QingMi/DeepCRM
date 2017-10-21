    protected Content nextContent() throws IOException
    {
        Content content = nextNonSentinelContent();
        if (content == null && !isFinished())
        {
            produceContent();
            content = nextNonSentinelContent();
        }
        return content;
    }
