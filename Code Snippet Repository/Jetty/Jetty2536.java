    public static String toRedirectURL(final HttpServletRequest request, String location)
    {
        if (!URIUtil.hasScheme(location))
        {
            StringBuilder url = new StringBuilder(128);
            URIUtil.appendSchemeHostPort(url,request.getScheme(),request.getServerName(),request.getServerPort());

            if (location.startsWith("/"))
            {
                // absolute in context
                location = URIUtil.canonicalEncodedPath(location);
            }
            else
            {
                // relative to request
                String path = request.getRequestURI();
                String parent = (path.endsWith("/")) ? path : URIUtil.parentPath(path);
                location = URIUtil.canonicalPath(URIUtil.addEncodedPaths(parent,location));
                if (!location.startsWith("/"))
                    url.append('/');
            }

            if (location == null)
                throw new IllegalStateException("path cannot be above root");
            url.append(location);

            location = url.toString();
        }

        return location;
    }
