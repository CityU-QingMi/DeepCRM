    public static String resolveUrl(
            String url, String context, PageContext pageContext)
    throws JspException {
        // don't touch absolute URLs
        if (isAbsoluteUrl(url))
            return url;
        
        // normalize relative URLs against a context root
        HttpServletRequest request =
            (HttpServletRequest) pageContext.getRequest();
        if (context == null) {
            if (url.startsWith("/"))
                return (request.getContextPath() + url);
            else
                return url;
        } else {
            if (!context.startsWith("/") || !url.startsWith("/")) {
                throw new JspTagException(
                "In URL tags, when the \"context\" attribute is specified, values of both \"context\" and \"url\" must start with \"/\".");
            }
            if (context.equals("/")) {
                // Don't produce string starting with '//', many
                // browsers interpret this as host name, not as
                // path on same host.
                return url;
            } else {
                return (context + url);
            }
        }
    }
