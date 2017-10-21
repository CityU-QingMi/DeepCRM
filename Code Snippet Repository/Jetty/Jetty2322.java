    protected void sendProxyRequest(HttpServletRequest clientRequest, HttpServletResponse proxyResponse, Request proxyRequest)
    {
        if (_log.isDebugEnabled())
        {
            StringBuilder builder = new StringBuilder(clientRequest.getMethod());
            builder.append(" ").append(clientRequest.getRequestURI());
            String query = clientRequest.getQueryString();
            if (query != null)
                builder.append("?").append(query);
            builder.append(" ").append(clientRequest.getProtocol()).append(System.lineSeparator());
            for (Enumeration<String> headerNames = clientRequest.getHeaderNames(); headerNames.hasMoreElements();)
            {
                String headerName = headerNames.nextElement();
                builder.append(headerName).append(": ");
                for (Enumeration<String> headerValues = clientRequest.getHeaders(headerName); headerValues.hasMoreElements();)
                {
                    String headerValue = headerValues.nextElement();
                    if (headerValue != null)
                        builder.append(headerValue);
                    if (headerValues.hasMoreElements())
                        builder.append(",");
                }
                builder.append(System.lineSeparator());
            }
            builder.append(System.lineSeparator());

            _log.debug("{} proxying to upstream:{}{}{}{}",
                    getRequestId(clientRequest),
                    System.lineSeparator(),
                    builder,
                    proxyRequest,
                    System.lineSeparator(),
                    proxyRequest.getHeaders().toString().trim());
        }

        proxyRequest.send(newProxyResponseListener(clientRequest, proxyResponse));
    }
