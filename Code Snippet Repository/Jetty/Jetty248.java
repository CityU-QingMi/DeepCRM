    protected HttpDestination destinationFor(String scheme, String host, int port)
    {
        if (!HttpScheme.HTTP.is(scheme) && !HttpScheme.HTTPS.is(scheme) &&
                !HttpScheme.WS.is(scheme) && !HttpScheme.WSS.is(scheme))
            throw new IllegalArgumentException("Invalid protocol " + scheme);

        scheme = scheme.toLowerCase(Locale.ENGLISH);
        host = host.toLowerCase(Locale.ENGLISH);
        port = normalizePort(scheme, port);

        Origin origin = new Origin(scheme, host, port);
        HttpDestination destination = destinations.get(origin);
        if (destination == null)
        {
            destination = transport.newHttpDestination(origin);
            addManaged(destination);
            HttpDestination existing = destinations.putIfAbsent(origin, destination);
            if (existing != null)
            {
                removeBean(destination);
                destination = existing;
            }
            else
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Created {}", destination);
            }
        }
        return destination;
    }
