    @Override
    public void badMessage(int status, String reason)
    {
        _httpConnection.getGenerator().setPersistent(false);
        try
        {
            // Need to call onRequest, so RequestLog can reports as much as possible
            onRequest(_metadata);
            getRequest().getHttpInput().earlyEOF();
        }
        catch (Exception e)
        {
            LOG.ignore(e);
        }

        onBadMessage(status, reason);
    }
