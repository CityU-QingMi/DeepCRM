    public Runnable onPushRequest(MetaData.Request request)
    {
        try
        {
            onRequest(request);
            getRequest().setAttribute("org.eclipse.jetty.pushed", Boolean.TRUE);
            onContentComplete();
            onRequestComplete();

            if (LOG.isDebugEnabled())
            {
                Stream stream = getStream();
                LOG.debug("HTTP2 PUSH Request #{}/{}:{}{} {} {}{}{}",
                        stream.getId(), Integer.toHexString(stream.getSession().hashCode()), System.lineSeparator(),
                        request.getMethod(), request.getURI(), request.getHttpVersion(),
                        System.lineSeparator(), request.getFields());
            }

            return this;
        }
        catch (BadMessageException x)
        {
            onBadMessage(x.getCode(), x.getReason());
            return null;
        }
        catch (Throwable x)
        {
            onBadMessage(HttpStatus.INTERNAL_SERVER_ERROR_500, null);
            return null;
        }
    }
