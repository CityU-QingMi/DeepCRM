    @Override
    protected void sendContent(HttpExchange exchange, HttpContent content, Callback callback)
    {
        if (content.isConsumed())
        {
            callback.succeeded();
        }
        else
        {
            int request = getHttpChannel().getRequest();
            Generator.Result result = generator.generateRequestContent(request, content.getByteBuffer(), content.isLast(), callback);
            getHttpChannel().flush(result);
        }
    }
