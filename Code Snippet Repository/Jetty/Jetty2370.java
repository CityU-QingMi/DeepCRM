    private String getBalancerMemberNameFromURL(HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        int idx = requestURI.lastIndexOf(";");
        if (idx > 0)
        {
            String requestURISuffix = requestURI.substring(idx + 1);
            if (requestURISuffix.startsWith(JSESSIONID_URL_PREFIX))
                return extractBalancerMemberNameFromSessionId(requestURISuffix.substring(JSESSIONID_URL_PREFIX.length()));
        }
        return null;
    }
