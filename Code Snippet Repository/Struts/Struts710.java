    protected void handleExtension(ActionMapping mapping, StringBuilder uri) {
        String extension = lookupExtension(mapping.getExtension());

        if (extension != null) {
            if (extension.length() == 0 || (extension.length() > 0 && uri.indexOf('.' + extension) == -1)) {
                if (extension.length() > 0) {
                    uri.append(".").append(extension);
                }
            }
        }
    }
