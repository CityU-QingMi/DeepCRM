    public void init(FilterConfig filterConfig) throws ServletException
    {
        tempdir=(File)filterConfig.getServletContext().getAttribute("javax.servlet.context.tempdir");
        _deleteFiles="true".equals(filterConfig.getInitParameter("deleteFiles"));
        String fileOutputBuffer = filterConfig.getInitParameter("fileOutputBuffer");
        if(fileOutputBuffer!=null)
            _fileOutputBuffer = Integer.parseInt(fileOutputBuffer);
        String maxFileSize = filterConfig.getInitParameter("maxFileSize");
        if (maxFileSize != null)
            _maxFileSize = Long.parseLong(maxFileSize.trim());
        String maxRequestSize = filterConfig.getInitParameter("maxRequestSize");
        if (maxRequestSize != null)
            _maxRequestSize = Long.parseLong(maxRequestSize.trim());

        _context=filterConfig.getServletContext();
        String mfks = filterConfig.getInitParameter("maxFormKeys");
        if (mfks!=null)
            _maxFormKeys=Integer.parseInt(mfks);
        _writeFilesWithFilenames = "true".equalsIgnoreCase(filterConfig.getInitParameter("writeFilesWithFilenames"));
    }
