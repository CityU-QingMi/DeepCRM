    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String fileName = request.getServletPath();
        byte[] dataBytes = loadContentFileBytes(fileName);

        response.setContentLength(dataBytes.length);
        response.setHeader("ETag","W/etag-"+fileName);

        String mime = mimeTypes.getMimeByExtension(fileName);
        if (mime == null)
            response.setContentType("application/octet-stream");
        else
            response.setContentType(mime);

        ServletOutputStream out = response.getOutputStream();
        out.write(dataBytes);
    }
