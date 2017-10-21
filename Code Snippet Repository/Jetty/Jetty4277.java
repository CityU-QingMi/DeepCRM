    protected boolean passConditionalHeaders(HttpServletRequest request, HttpServletResponse response, File file) throws IOException
    {
        long date = 0;

        if ((date = request.getDateHeader("if-unmodified-since")) > 0)
        {
            if (file.lastModified() / 1000 > date / 1000)
            {
                response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
                return false;
            }
        }

        if ((date = request.getDateHeader("if-modified-since")) > 0)
        {
            if (file.lastModified() / 1000 <= date / 1000)
            {
                response.reset();
                response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                response.flushBuffer();
                return false;
            }
        }
        return true;
    }
