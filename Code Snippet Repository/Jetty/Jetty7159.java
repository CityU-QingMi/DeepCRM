    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp)
    {
        LOG.debug("Creating BrowserSocket");

        if (req.getSubProtocols() != null)
        {
            if (!req.getSubProtocols().isEmpty())
            {
                String subProtocol = req.getSubProtocols().get(0);
                resp.setAcceptedSubProtocol(subProtocol);
            }
        }

        String ua = req.getHeader("User-Agent");
        String rexts = req.getHeader("Sec-WebSocket-Extensions");

        // manually negotiate extensions
        List<ExtensionConfig> negotiated = new ArrayList<>();
        // adding frame debug
        negotiated.add(new ExtensionConfig("@frame-capture; output-dir=target"));
        for (ExtensionConfig config : req.getExtensions())
        {
            if (config.getName().equals("permessage-deflate"))
            {
                // what we are interested in here
                negotiated.add(config);
                continue;
            }
            // skip all others
        }

        resp.setExtensions(negotiated);

        LOG.debug("User-Agent: {}",ua);
        LOG.debug("Sec-WebSocket-Extensions (Request) : {}",rexts);
        LOG.debug("Sec-WebSocket-Protocol (Request): {}",req.getHeader("Sec-WebSocket-Protocol"));
        LOG.debug("Sec-WebSocket-Protocol (Response): {}",resp.getAcceptedSubProtocol());

        req.getExtensions();
        return new BrowserSocket(ua,rexts);
    }
