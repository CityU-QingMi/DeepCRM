        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
        {
            if (request instanceof HttpServletRequest)
            {
                List<String> violations = (List<String>) request.getAttribute("org.eclipse.jetty.http.compliance.violations");
                if (violations != null)
                {
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    int i = 0;
                    for (String violation : violations)
                    {
                        httpResponse.setHeader("X-Http-Violation-" + (i++), violation);
                    }
                }
            }
            chain.doFilter(request, response);
        }
