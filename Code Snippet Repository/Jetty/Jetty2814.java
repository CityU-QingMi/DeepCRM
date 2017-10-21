    public void onBadMessage(int status, String reason)
    {
        if (status < 400 || status > 599)
            status = HttpStatus.BAD_REQUEST_400;

        Action action;
        try
        {
            action=_state.handling();
        }
        catch(IllegalStateException e)
        {
            // The bad message cannot be handled in the current state, so throw
            // to hopefull somebody that can handle
            abort(e);
            throw new BadMessageException(status,reason);
        }

        try
        {
            if (action==Action.DISPATCH)
            {
                ByteBuffer content=null;
                HttpFields fields=new HttpFields();

                ErrorHandler handler=getServer().getBean(ErrorHandler.class);
                if (handler!=null)
                    content=handler.badMessageError(status,reason,fields);

                sendResponse(new MetaData.Response(HttpVersion.HTTP_1_1,status,reason,fields,BufferUtil.length(content)),content ,true);
            }
        }
        catch (IOException e)
        {
            LOG.debug(e);
        }
        finally
        {
            // TODO: review whether it's the right state to check.
            if (_state.unhandle()==Action.COMPLETE)
                _state.onComplete();
            else
                throw new IllegalStateException(); // TODO: don't throw from finally blocks !
            onCompleted();
        }
    }
