    @Override
    public String matchAndApply(String target, HttpServletRequest request,
            HttpServletResponse response) throws IOException
    {
        String requestHeaderValue = request.getHeader(_header);
        
        if (requestHeaderValue != null)
            if (_headerValue == null || _headerValue.equals(requestHeaderValue))
                apply(target, requestHeaderValue, request, response);
        
        return null;
    }
