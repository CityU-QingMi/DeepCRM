    private void applyEncoding(HttpServletRequest request, String encoding) {
        try {
            if (!encoding.equals(request.getCharacterEncoding())) {
                // if the encoding is already correctly set and the parameters have been already read
                // do not try to set encoding because it is useless and will cause an error
                request.setCharacterEncoding(encoding);
            }
        } catch (Exception e) {
            LOG.error("Error setting character encoding to '{}' - ignoring.", encoding, e);
        }
    }
