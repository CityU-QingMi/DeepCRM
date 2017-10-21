    public void forwardSuccess(List<Response.ResponseListener> listeners, Response response)
    {
        notifyBegin(listeners, response);
        for (Iterator<HttpField> iterator = response.getHeaders().iterator(); iterator.hasNext();)
        {
            HttpField field = iterator.next();
            if (!notifyHeader(listeners, response, field))
                iterator.remove();
        }
        notifyHeaders(listeners, response);
        if (response instanceof ContentResponse)
            notifyContent(listeners, response, ByteBuffer.wrap(((ContentResponse)response).getContent()), Callback.NOOP);
        notifySuccess(listeners, response);
    }
