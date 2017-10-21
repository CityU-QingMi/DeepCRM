    @Override
    protected String filterServerResponseHeader(HttpServletRequest request, Response serverResponse, String headerName, String headerValue)
    {
        if (_proxyPassReverse && REVERSE_PROXY_HEADERS.contains(headerName))
        {
            URI locationURI = URI.create(headerValue).normalize();
            if (locationURI.isAbsolute() && isBackendLocation(locationURI))
            {
                StringBuilder newURI = URIUtil.newURIBuilder(request.getScheme(), request.getServerName(), request.getServerPort());
                String component = locationURI.getRawPath();
                if (component != null)
                    newURI.append(component);
                component = locationURI.getRawQuery();
                if (component != null)
                    newURI.append('?').append(component);
                component = locationURI.getRawFragment();
                if (component != null)
                    newURI.append('#').append(component);
                return URI.create(newURI.toString()).normalize().toString();
            }
        }
        return headerValue;
    }
