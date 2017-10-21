        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
        {
            String uri = request.getRequestURI();
            String queryString = request.getQueryString();
            if ("/initialCall".equals(uri)) 
            {
                AsyncContext async = request.startAsync();
                async.dispatch("/firstDispatchWithNewQueryString?newQueryString=initialValue");
                assertEquals("initialParam=right", queryString);
            } 
            else if ("/firstDispatchWithNewQueryString".equals(uri)) 
            {
                AsyncContext async = request.startAsync();
                async.dispatch("/secondDispatchNewValueForExistingQueryString?newQueryString=newValue");
                assertEquals("newQueryString=initialValue&initialParam=right", queryString);
            } 
            else 
            {
                response.setContentType("text/html");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println("<h1>woohhooooo</h1>");
                assertEquals("newQueryString=newValue&initialParam=right", queryString);
            }
        }
