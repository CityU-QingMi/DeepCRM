    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        String included_paths = filterConfig.getInitParameter("includedPaths");
        String excluded_paths = filterConfig.getInitParameter("excludedPaths");
        String included_mime_types = filterConfig.getInitParameter("includedMimeTypes");
        String excluded_mime_types = filterConfig.getInitParameter("excludedMimeTypes");
        String included_http_methods = filterConfig.getInitParameter("includedHttpMethods");
        String excluded_http_methods = filterConfig.getInitParameter("excludedHttpMethods");

        if (included_paths != null)
        {
            _paths.include(StringUtil.csvSplit(included_paths));
        }
        if (excluded_paths != null)
        {
            _paths.exclude(StringUtil.csvSplit(excluded_paths));
        }
        if (included_mime_types != null)
        {
            _mimeTypes.include(StringUtil.csvSplit(included_mime_types));
        }
        if (excluded_mime_types != null)
        {
            _mimeTypes.exclude(StringUtil.csvSplit(excluded_mime_types));
        }
        if (included_http_methods != null)
        {
            _httpMethods.include(StringUtil.csvSplit(included_http_methods));
        }
        if (excluded_http_methods != null)
        {
            _httpMethods.exclude(StringUtil.csvSplit(excluded_http_methods));
        }
    }
