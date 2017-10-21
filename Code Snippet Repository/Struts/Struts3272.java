    protected String readContentTypeEncoding(HttpServletRequest request) {
        String contentTypeEncoding = request.getHeader("Content-Type");
        LOG.debug("Content Type encoding from request: {}", contentTypeEncoding);

        if (contentTypeEncoding != null && contentTypeEncoding.contains(";charset=")) {
            contentTypeEncoding = contentTypeEncoding.substring(contentTypeEncoding.indexOf(";charset=") + ";charset=".length()).trim();
        } else {
            contentTypeEncoding = defaultEncoding;
        }

        LOG.debug("Content Type encoding to be used in de-serialisation: {}", contentTypeEncoding);
        return contentTypeEncoding;
    }
