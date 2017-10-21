    private List<String> getAccessControlRequestHeaders(HttpServletRequest request)
    {
        String accessControlRequestHeaders = request.getHeader(ACCESS_CONTROL_REQUEST_HEADERS_HEADER);
        LOG.debug("{} is {}", ACCESS_CONTROL_REQUEST_HEADERS_HEADER, accessControlRequestHeaders);
        if (accessControlRequestHeaders == null)
            return Collections.emptyList();

        List<String> requestedHeaders = new ArrayList<String>();
        String[] headers = StringUtil.csvSplit(accessControlRequestHeaders);
        for (String header : headers)
        {
            String h = header.trim();
            if (h.length() > 0)
                requestedHeaders.add(h);
        }
        return requestedHeaders;
    }
