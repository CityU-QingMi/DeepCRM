    @Override
    protected HttpClient newHttpClient()
    {
        ServletConfig config = getServletConfig();
        String scriptRoot = config.getInitParameter(SCRIPT_ROOT_INIT_PARAM);
        if (scriptRoot == null)
            throw new IllegalArgumentException("Mandatory parameter '" + SCRIPT_ROOT_INIT_PARAM + "' not configured");
        int selectors = Math.max(1, Runtime.getRuntime().availableProcessors() / 2);
        String value = config.getInitParameter("selectors");
        if (value != null)
            selectors = Integer.parseInt(value);
        return new HttpClient(new ProxyHttpClientTransportOverFCGI(selectors, scriptRoot), null);
    }
