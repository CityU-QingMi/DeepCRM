    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String fileName = request.getServletPath();
        byte[] dataBytes = loadContentFileBytes(fileName);

        response.setContentLength(dataBytes.length);
        response.setHeader("ETag","W/etag-"+fileName);
        if (fileName.endsWith(".js"))
        {
            // intentionally long-form content type to test ";" splitting in code
            response.setContentType("text/javascript; charset=utf-8");
        }
        else
        {
            String mime = mimeTypes.getMimeByExtension(fileName);
            if (mime != null)
                response.setContentType(mime);
        }
        ServletOutputStream out = response.getOutputStream();
        out.write(dataBytes);
    }
