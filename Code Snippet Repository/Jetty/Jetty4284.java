    @Override
    public void onTimeout(AsyncEvent event) throws IOException
    {
        HttpServletRequest request = (HttpServletRequest)event.getSuppliedRequest();
        HttpServletResponse response = (HttpServletResponse)event.getSuppliedResponse();

        String fileName = (String)request.getAttribute("filename");
        byte[] dataBytes = loadContentFileBytes(fileName);

        response.setContentLength(dataBytes.length);

        ServletOutputStream out = response.getOutputStream();

        if (fileName.endsWith("txt"))
            response.setContentType("text/plain");
        else if (fileName.endsWith("mp3"))
            response.setContentType("audio/mpeg");
        response.setHeader("ETag","W/etag-" + fileName);

        out.write(dataBytes);

        event.getAsyncContext().complete();
    }
