    @Override
    protected void onResponseContent(HttpServletRequest request, HttpServletResponse response, Response proxyResponse, byte[] buffer, int offset, int length, Callback callback)
    {
        try
        {
            if (_log.isDebugEnabled())
                _log.debug("{} proxying content to downstream: {} bytes", getRequestId(request), length);
            StreamWriter writeListener = (StreamWriter)request.getAttribute(WRITE_LISTENER_ATTRIBUTE);
            if (writeListener == null)
            {
                writeListener = newWriteListener(request, proxyResponse);
                request.setAttribute(WRITE_LISTENER_ATTRIBUTE, writeListener);

                // Set the data to write before calling setWriteListener(), because
                // setWriteListener() may trigger the call to onWritePossible() on
                // a different thread and we would have a race.
                writeListener.data(buffer, offset, length, callback);

                // Setting the WriteListener triggers an invocation to onWritePossible().
                response.getOutputStream().setWriteListener(writeListener);
            }
            else
            {
                writeListener.data(buffer, offset, length, callback);
                writeListener.onWritePossible();
            }
        }
        catch (Throwable x)
        {
            callback.failed(x);
            proxyResponse.abort(x);
        }
    }
