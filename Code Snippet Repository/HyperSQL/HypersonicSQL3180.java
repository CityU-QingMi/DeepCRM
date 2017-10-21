    private String getMimeTypeString(String name) {

        int    pos;
        String key;
        String mimeType;

        if (name == null) {
            return ServerConstants.SC_DEFAULT_WEB_MIME;
        }

        pos      = name.lastIndexOf('.');
        key      = null;
        mimeType = null;

        // first search user-specified mapping
        if (pos >= 0) {
            key      = name.substring(pos).toLowerCase();
            mimeType = server.serverProperties.getProperty(key);
        }

        // if not found, search default mapping
        if (mimeType == null && key.length() > 1) {
            mimeType = ResourceBundleHandler.getString(hnd_content_types,
                    key.substring(1));
        }

        return mimeType == null ? ServerConstants.SC_DEFAULT_WEB_MIME
                                : mimeType;
    }
