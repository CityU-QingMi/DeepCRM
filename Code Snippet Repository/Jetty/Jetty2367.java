    @Override
    protected String rewriteTarget(HttpServletRequest request)
    {
        BalancerMember balancerMember = selectBalancerMember(request);
        if (_log.isDebugEnabled())
            _log.debug("Selected {}", balancerMember);
        String path = request.getRequestURI();
        String query = request.getQueryString();
        if (query != null)
            path += "?" + query;
        return URI.create(balancerMember.getProxyTo() + "/" + path).normalize().toString();
    }
