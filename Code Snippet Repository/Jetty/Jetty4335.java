    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String fileName = request.getServletPath();
        byte[] dataBytes = loadContentFileBytes(fileName);

        ServletOutputStream out = response.getOutputStream();

        // set content-length of uncompressed content (GzipHandler should handle this)
        response.setContentLength(dataBytes.length);
        
        if (fileName.endsWith("txt"))
            response.setContentType("text/plain");
        else if (fileName.endsWith("mp3"))
            response.setContentType("audio/mpeg");
        response.setHeader("ETag","W/etag-"+fileName);

        for ( int i = 0 ; i < dataBytes.length ; i++)
        {
            out.write(dataBytes[i]);
            // flush using response object (not the stream itself)
            response.flushBuffer();
        }
    }
