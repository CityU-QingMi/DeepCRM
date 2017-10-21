    protected String rewriteTarget(HttpServletRequest clientRequest)
    {
        if (!validateDestination(clientRequest.getServerName(), clientRequest.getServerPort()))
            return null;

        StringBuffer target = clientRequest.getRequestURL();
        String query = clientRequest.getQueryString();
        if (query != null)
            target.append("?").append(query);
        return target.toString();
    }
