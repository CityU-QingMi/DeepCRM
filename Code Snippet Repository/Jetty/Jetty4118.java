        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
        {
            if (Boolean.parseBoolean(request.getParameter("async")) && !Boolean.parseBoolean((String)request.getAttribute("async")))
            {
                request.setAttribute("async","true");
                AsyncContext async = Boolean.parseBoolean(request.getParameter("wrap"))
                    ?request.startAsync(request,response)
                    :request.startAsync();
                async.dispatch();
                return;
            }
            chain.doFilter(request,response);
        }
