    @Override
    public void init() throws ServletException
    {
        _log = createLogger();

        ServletConfig config = getServletConfig();

        _preserveHost = Boolean.parseBoolean(config.getInitParameter("preserveHost"));

        _hostHeader = config.getInitParameter("hostHeader");

        _viaHost = config.getInitParameter("viaHost");
        if (_viaHost == null)
            _viaHost = viaHost();

        try
        {
            _client = createHttpClient();

            // Put the HttpClient in the context to leverage ContextHandler.MANAGED_ATTRIBUTES
            getServletContext().setAttribute(config.getServletName() + ".HttpClient", _client);

            String whiteList = config.getInitParameter("whiteList");
            if (whiteList != null)
                getWhiteListHosts().addAll(parseList(whiteList));

            String blackList = config.getInitParameter("blackList");
            if (blackList != null)
                getBlackListHosts().addAll(parseList(blackList));
        }
        catch (Exception e)
        {
            throw new ServletException(e);
        }
    }
