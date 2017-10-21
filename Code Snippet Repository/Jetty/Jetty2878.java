    public void sendContent(ByteBuffer content, final Callback callback)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("sendContent(buffer={},{})", BufferUtil.toDetailString(content), callback);

        _written += content.remaining();
        write(content, true, new Callback.Nested(callback)
        {
            @Override
            public void succeeded()
            {
                closed();
                super.succeeded();
            }

            @Override
            public void failed(Throwable x)
            {
                abort(x);
                super.failed(x);
            }
        });
    }
