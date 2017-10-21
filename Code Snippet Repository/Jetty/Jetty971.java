        @Override
        protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            AsyncContext asyncContext = (AsyncContext)request.getAttribute(AsyncContext.class.getName());
            if (asyncContext == null)
            {
                AsyncContext context = request.startAsync();
                context.setTimeout(0);
                request.setAttribute(AsyncContext.class.getName(), context);
                context.addListener(this);
            }
            else
            {
                throw new ServletException();
            }
        }
