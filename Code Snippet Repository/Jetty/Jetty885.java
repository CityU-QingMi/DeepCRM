    @Override
    public void init() throws ServletException
    {
        super.init();

        String value = getInitParameter(SCRIPT_PATTERN_INIT_PARAM);
        if (value == null)
            value = "(.+?\\.php)";
        scriptPattern = Pattern.compile(value);

        originalURIAttribute = getInitParameter(ORIGINAL_URI_ATTRIBUTE_INIT_PARAM);
        originalQueryAttribute = getInitParameter(ORIGINAL_QUERY_ATTRIBUTE_INIT_PARAM);

        fcgiHTTPS = Boolean.parseBoolean(getInitParameter(FASTCGI_HTTPS_INIT_PARAM));
    }
