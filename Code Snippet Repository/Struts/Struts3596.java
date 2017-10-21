    public ContentTypeHandler getHandlerForResponse(HttpServletRequest request, HttpServletResponse res) {

        String extension = getExtensionIfPresent(request.getRequestURI());
        if (extension == null) {
            extension = defaultExtension;
            final String acceptHeader = request.getHeader("accept") ;
            if (acceptHeader != null) {
                final String[] types = acceptHeader.split(",");
                for (final String type : types) {
                    final ContentTypeHandler handler = findHandler(type);
                    if (handler != null) {
                        return handler;
                    }
                }
            }
        }
        return handlersByExtension.get(extension);
    }
