    protected boolean isRequestSizePermitted(HttpServletRequest request) {
        // if maxSize is specified as -1, there is no sanity check and it's
        // safe to return true for any request, delegating the failure
        // checks later in the upload process.
        if (maxSize == -1 || request == null) {
            return true;
        }

        return request.getContentLength() < maxSize;
    }
