        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
        {
            Request baseRequest = Request.getBaseRequest(request);

            if (baseRequest.isPush() && baseRequest.getRequestURI().contains("tiles") )
            {
                String uri = baseRequest.getRequestURI().replace("tiles","pushed").substring(baseRequest.getContextPath().length());
                request.getRequestDispatcher(uri).forward(request,response);
                return;
            }

            chain.doFilter(request,response);
        }
