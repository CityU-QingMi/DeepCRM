    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest http_request = (HttpServletRequest)request;
        HttpServletResponse http_response = (HttpServletResponse)response;

        if (super.shouldFilter(http_request,http_response))
        {
            for (ConfiguredHeader header : _configuredHeaders)
            {
                if (header.isDate())
                {
                    long header_value = System.currentTimeMillis() + header.getMsOffset();
                    if (header.isAdd())
                    {
                        http_response.addDateHeader(header.getName(),header_value);
                    }
                    else
                    {
                        http_response.setDateHeader(header.getName(),header_value);
                    }
                }
                else // constant header value
                {
                    if (header.isAdd())
                    {
                        http_response.addHeader(header.getName(),header.getValue());
                    }
                    else
                    {
                        http_response.setHeader(header.getName(),header.getValue());
                    }
                }
            }
        }

        chain.doFilter(request,response);
    }
