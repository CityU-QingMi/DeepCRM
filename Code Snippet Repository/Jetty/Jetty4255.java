    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        super.init(filterConfig);
        String header_config = filterConfig.getInitParameter("headerConfig");

        if (header_config != null)
        {
            String[] configs = StringUtil.csvSplit(header_config);
            for (String config : configs)
                _configuredHeaders.add(parseHeaderConfiguration(config));
        }

        if (LOG.isDebugEnabled())
            LOG.debug(this.toString());
    }
