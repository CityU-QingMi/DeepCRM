    private void minimalErrorResponse(Throwable failure)
    {
        try
        {
            Integer code=(Integer)_request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
            _response.reset(true);
            _response.setStatus(code == null ? 500 : code);
            _response.flushBuffer();
        }
        catch (Throwable x)
        {
            failure.addSuppressed(x);
            abort(failure);
        }
    }
