    public ContentTypeHandler getHandlerForRequest(HttpServletRequest request) {
        ContentTypeHandler handler = null;
        String contentType = request.getContentType();
        if (contentType != null) {
            handler = handlersByContentType.get(contentType);
            if (handler == null) {
                // strip off encoding and search again (e.g., application/json;charset=ISO-8859-1)
                int index = contentType.indexOf(';');
                if (index != -1) {
                    contentType = contentType.substring(0, index).trim();
                }
                handler = handlersByContentType.get(contentType);
            }
        }
        if (handler == null) {
            String extension = findExtension(request.getRequestURI());
            handler = handlersByExtension.get(extension);
        }
        return handler;
    }
