    public void handleMove(HttpServletRequest request, HttpServletResponse response, String pathInContext, File file)
        throws ServletException, IOException, URISyntaxException
    {
        String newPath = URIUtil.canonicalEncodedPath(request.getHeader("new-uri"));
        if (newPath == null)
        {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String contextPath = request.getContextPath();
        if (contextPath != null && !newPath.startsWith(contextPath))
        {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return;
        }
        String newInfo = newPath;
        if (contextPath != null)
            newInfo = newInfo.substring(contextPath.length());

        String new_resource = URIUtil.addEncodedPaths(_baseURI,newInfo);
        File new_file=new File(new URI(new_resource));

        file.renameTo(new_file);

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        response.flushBuffer();
    }
