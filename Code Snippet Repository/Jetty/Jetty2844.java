    @Override
    public void send(MetaData.Response info, boolean head, ByteBuffer content, boolean lastContent, Callback callback)
    {
        if (info == null)
        {
            if (!lastContent && BufferUtil.isEmpty(content))
            {
                callback.succeeded();
                return;
            }
        }
        else
        {
            // If we are still expecting a 100 continues when we commit
            if (_channel.isExpecting100Continue())
                // then we can't be persistent
                _generator.setPersistent(false);
        }

        if(_sendCallback.reset(info,head,content,lastContent,callback))
            _sendCallback.iterate();
    }
