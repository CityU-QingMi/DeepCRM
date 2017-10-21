    @Override
    protected void sendContent(HttpExchange exchange, HttpContent content, Callback callback)
    {
        if (content.isConsumed())
        {
            callback.succeeded();
        }
        else
        {
            Stream stream = getHttpChannel().getStream();
            Supplier<HttpFields> trailers = exchange.getRequest().getTrailers();
            DataFrame frame = new DataFrame(stream.getId(), content.getByteBuffer(), trailers == null && content.isLast());
            stream.data(frame, callback);
        }
    }
