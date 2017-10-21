        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
        {
            HttpServletRequest http_request = (HttpServletRequest)request;
            HttpServletResponse http_response = (HttpServletResponse)response;

            if (super.shouldFilter(http_request,http_response))
            {
                http_response.setHeader("X-Custom-Value","1");
            }

            chain.doFilter(request,response);
        }
