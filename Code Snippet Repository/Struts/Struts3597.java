    private ContentTypeHandler findHandler(final String type) {
        ContentTypeHandler handler = handlersByContentType.get(type);
        if (handler == null) {
            // strip off encoding and search again (e.g., application/json;charset=ISO-8859-1)
            final int index = type.indexOf(';');
            if (index != -1) {
                return handlersByContentType.get(type.substring(0, index).trim());
            }
        }
        return handler;
    }
