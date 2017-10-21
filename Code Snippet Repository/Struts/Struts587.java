    public static void include( String relativePath, Writer writer, ServletRequest request,
                                HttpServletResponse response, String encoding ) throws ServletException, IOException {
        String resourcePath = getContextRelativePath(request, relativePath);
        RequestDispatcher rd = request.getRequestDispatcher(resourcePath);

        if (rd == null) {
            throw new ServletException("Not a valid resource path:" + resourcePath);
        }

        PageResponse pageResponse = new PageResponse(response);

        // Include the resource
        rd.include(request, pageResponse);

        if (encoding != null) {
            // Use given encoding
            pageResponse.getContent().writeTo(writer, encoding);
        } else {
            //use the platform specific encoding
            pageResponse.getContent().writeTo(writer, systemEncoding);
        }
    }
