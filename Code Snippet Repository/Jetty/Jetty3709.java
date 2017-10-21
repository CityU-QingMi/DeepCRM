        @Override
        public void handle(String s, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            baseRequest.setHandled(true);
            
            String n = (String)baseRequest.getAttribute("async");
            if (n==null)
            {
                AsyncContext async=baseRequest.startAsync();
                async.setTimeout(1000);
                baseRequest.setAttribute("async", name);
                async.dispatch();
            }
            else
            {
                response.getWriter().print(n);
            }
        }
