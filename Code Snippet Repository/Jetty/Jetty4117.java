        @Override
        public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            AsyncContext async = Boolean.parseBoolean(request.getParameter("wrap"))
                ?request.startAsync(request,response)
                :request.startAsync();

            if (Boolean.parseBoolean(request.getParameter("encode")))
                async.dispatch("/test%20servlet"+URIUtil.encodePath(request.getPathInfo()));
            else
                async.dispatch("/test servlet/path info"+request.getPathInfo());
            return;
        }
