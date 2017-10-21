    protected void onServerResponseHeaders(HttpServletRequest clientRequest, HttpServletResponse proxyResponse, Response serverResponse)
    {
        for (HttpField field : serverResponse.getHeaders())
        {
            String headerName = field.getName();
            String lowerHeaderName = headerName.toLowerCase(Locale.ENGLISH);
            if (HOP_HEADERS.contains(lowerHeaderName))
                continue;

            String newHeaderValue = filterServerResponseHeader(clientRequest, serverResponse, headerName, field.getValue());
            if (newHeaderValue == null || newHeaderValue.trim().length() == 0)
                continue;

            proxyResponse.addHeader(headerName, newHeaderValue);
        }

        if (_log.isDebugEnabled())
        {
            StringBuilder builder = new StringBuilder(System.lineSeparator());
            builder.append(clientRequest.getProtocol()).append(" ").append(proxyResponse.getStatus())
                    .append(" ").append(serverResponse.getReason()).append(System.lineSeparator());
            for (String headerName : proxyResponse.getHeaderNames())
            {
                builder.append(headerName).append(": ");
                for (Iterator<String> headerValues = proxyResponse.getHeaders(headerName).iterator(); headerValues.hasNext(); )
                {
                    String headerValue = headerValues.next();
                    if (headerValue != null)
                        builder.append(headerValue);
                    if (headerValues.hasNext())
                        builder.append(",");
                }
                builder.append(System.lineSeparator());
            }
            _log.debug("{} proxying to downstream:{}{}{}{}{}",
                    getRequestId(clientRequest),
                    System.lineSeparator(),
                    serverResponse,
                    System.lineSeparator(),
                    serverResponse.getHeaders().toString().trim(),
                    System.lineSeparator(),
                    builder);
        }
    }
