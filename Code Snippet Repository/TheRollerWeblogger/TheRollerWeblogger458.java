    private String getAbsoluteUrl(HttpServletRequest request) {

        String url = null;

        String fullUrl = null;

        if (!request.isSecure()) {
            fullUrl = request.getRequestURL().toString();
        } else {
            fullUrl = "http://" + request.getServerName()
                    + request.getContextPath();
        }

        // if the uri is only "/" then we are basically done
        if ("/".equals(request.getRequestURI())) {
            if (log.isDebugEnabled()) {
                log.debug(fullUrl.substring(0, fullUrl.length() - 1));
            }
            return fullUrl.substring(0, fullUrl.length() - 1);
        }

        // find first "/" starting after hostname is specified
        int index = fullUrl.indexOf('/',
                fullUrl.indexOf(request.getServerName()));

        if (index != -1) {
            // extract just the part leading up to uri
            url = fullUrl.substring(0, index);
        } else {
            url = fullUrl.trim();
        }

        // then just add on the context path
        url += request.getContextPath();

        // make certain that we don't end with a /
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        return url;
    }
