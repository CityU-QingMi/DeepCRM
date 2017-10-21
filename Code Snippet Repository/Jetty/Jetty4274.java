    public void handleDelete(HttpServletRequest request, HttpServletResponse response, String pathInContext, File file) throws ServletException, IOException
    {
        try
        {
            // delete the file
            if (file.delete())
            {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                response.flushBuffer();
            }
            else
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
        catch (SecurityException sex)
        {
            _context.log(sex.toString(),sex);
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
