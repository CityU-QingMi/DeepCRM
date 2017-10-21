    @Override
    public String matchAndApply(String target, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        if(_virtualHosts != null && _virtualHosts.length > 0 )
        {
            String requestHost = normalizeHostname( request.getServerName() );
            for( String ruleHost : _virtualHosts )
            {
                if(ruleHost == null || ruleHost.equalsIgnoreCase(requestHost)
                        || (ruleHost.startsWith("*.") && ruleHost.regionMatches(true,2,requestHost,requestHost.indexOf(".")+1,ruleHost.length()-2)))
                    return apply(target, request, response);
            }
        }
        else
        {
            return apply(target, request, response);
        }
        return null;
    }
