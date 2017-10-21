    void clearURLHandlers() throws Exception {
        final Field handlersFields = URL.class.getDeclaredField("handlers");
        if (handlersFields != null) {
            if (!handlersFields.isAccessible()) {
                handlersFields.setAccessible(true);
            }
            @SuppressWarnings("unchecked")
            final
            Hashtable<String, URLStreamHandler> handlers = (Hashtable<String, URLStreamHandler>) handlersFields
                    .get(null);
            if (handlers != null) {
                handlers.clear();
            }
        }
    }
