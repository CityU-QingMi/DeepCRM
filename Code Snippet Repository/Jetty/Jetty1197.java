    @Override
    protected void sendHeaders(HttpExchange exchange, final HttpContent content, final Callback callback)
    {
        HttpRequest request = exchange.getRequest();
        String path = relativize(request.getPath());
        HttpURI uri = HttpURI.createHttpURI(request.getScheme(), request.getHost(), request.getPort(), path, null, request.getQuery(), null);
        MetaData.Request metaData = new MetaData.Request(request.getMethod(), uri, HttpVersion.HTTP_2, request.getHeaders());
        Supplier<HttpFields> trailers = request.getTrailers();
        metaData.setTrailerSupplier(trailers);
        HeadersFrame headersFrame = new HeadersFrame(metaData, null, trailers == null && !content.hasContent());
        HttpChannelOverHTTP2 channel = getHttpChannel();
        Promise<Stream> promise = new Promise<Stream>()
        {
            @Override
            public void succeeded(Stream stream)
            {
                getHttpChannel().setStream(stream);
                stream.setIdleTimeout(request.getIdleTimeout());

                if (content.hasContent() && !expects100Continue(request))
                {
                    boolean advanced = content.advance();
                    boolean lastContent = trailers == null && content.isLast();
                    if (advanced || lastContent)
                    {
                        DataFrame dataFrame = new DataFrame(stream.getId(), content.getByteBuffer(), lastContent);
                        stream.data(dataFrame, callback);
                        return;
                    }
                }
                callback.succeeded();
            }

            @Override
            public void failed(Throwable failure)
            {
                callback.failed(failure);
            }
        };
        // TODO optimize the send of HEADERS and DATA frames.
        channel.getSession().newStream(headersFrame, promise, channel.getStreamListener());
    }
