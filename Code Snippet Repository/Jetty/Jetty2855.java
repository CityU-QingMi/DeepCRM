    protected Content nextNonSentinelContent()
    {
        while (true)
        {            
            // Get the next content (or EOF)
            Content content = nextInterceptedContent();
            
            // If it is EOF, consume it here
            if (content instanceof SentinelContent)
            {
                // Consume the EOF content, either if it was original content
                // or if it was produced by interception
                consume(content);
                continue;
            }

            return content;
        }
    }
