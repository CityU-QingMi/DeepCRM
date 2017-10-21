    @Override
    public void init(FilterConfig config) throws ServletException
    {
        String associatePeriod = config.getInitParameter("associatePeriod");
        if (associatePeriod != null)
            _associatePeriod = Long.parseLong(associatePeriod);

        String maxAssociations = config.getInitParameter("maxAssociations");
        if (maxAssociations != null)
            _maxAssociations = Integer.parseInt(maxAssociations);

        String hosts = config.getInitParameter("hosts");
        if (hosts != null)
            Collections.addAll(_hosts, StringUtil.csvSplit(hosts));

        String ports = config.getInitParameter("ports");
        if (ports != null)
            for (String p : StringUtil.csvSplit(ports))
                _ports.add(Integer.parseInt(p));

        _useQueryInKey = Boolean.parseBoolean(config.getInitParameter("useQueryInKey"));
        
        // Expose for JMX.
        config.getServletContext().setAttribute(config.getFilterName(), this);

        if (LOG.isDebugEnabled())
            LOG.debug("period={} max={} hosts={} ports={}", _associatePeriod, _maxAssociations, _hosts, _ports);
    }
