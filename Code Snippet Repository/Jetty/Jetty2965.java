    public void sendRedirect(int code, String location) throws IOException
    {
        if ((code < HttpServletResponse.SC_MULTIPLE_CHOICES) || (code >= HttpServletResponse.SC_BAD_REQUEST))
            throw new IllegalArgumentException("Not a 3xx redirect code");

        if (isIncluding())
            return;

        if (location == null)
            throw new IllegalArgumentException();

        if (!URIUtil.hasScheme(location))
        {
            StringBuilder buf = _channel.getRequest().getRootURL();
            if (location.startsWith("/"))
            {
                // absolute in context
                location=URIUtil.canonicalEncodedPath(location);
            }
            else
            {
                // relative to request
                String path=_channel.getRequest().getRequestURI();
                String parent=(path.endsWith("/"))?path:URIUtil.parentPath(path);
                location=URIUtil.canonicalEncodedPath(URIUtil.addEncodedPaths(parent,location));
                if (!location.startsWith("/"))
                    buf.append('/');
            }

            if(location==null)
                throw new IllegalStateException("path cannot be above root");
            buf.append(location);

            location=buf.toString();
        }

        resetBuffer();
        setHeader(HttpHeader.LOCATION, location);
        setStatus(code);
        closeOutput();
    }
