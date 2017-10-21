    @Override
    public Request onRequestContent(final ContentListener listener)
    {
        return requestListener(new ContentListener()
        {
            @Override
            public void onContent(Request request, ByteBuffer content)
            {
                listener.onContent(request, content);
            }
        });
    }
