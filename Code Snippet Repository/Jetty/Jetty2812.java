    public void onRequest(MetaData.Request request)
    {
        _requests.incrementAndGet();
        _request.setTimeStamp(System.currentTimeMillis());
        HttpFields fields = _response.getHttpFields();
        if (_configuration.getSendDateHeader() && !fields.contains(HttpHeader.DATE))
            fields.put(_connector.getServer().getDateField());

        long idleTO=_configuration.getIdleTimeout();
        _oldIdleTimeout=getIdleTimeout();
        if (idleTO>=0 && _oldIdleTimeout!=idleTO)
            setIdleTimeout(idleTO);

        request.setTrailerSupplier(_trailerSupplier);
        _request.setMetaData(request);

        if (LOG.isDebugEnabled())
            LOG.debug("REQUEST for {} on {}{}{} {} {}{}{}",request.getURIString(),this,System.lineSeparator(),
                    request.getMethod(),request.getURIString(),request.getHttpVersion(),System.lineSeparator(),
                    request.getFields());
    }
