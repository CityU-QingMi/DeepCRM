        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            if (request.getParameter("dispatch") != null)
            {
                AsyncContext asyncContext = request.startAsync(request, response);
                asyncContext.dispatch(dispatchPath);
            }
            else
            {
                response.getOutputStream().print("doGet:getServletPath:" + request.getServletPath() + "\n");
                response.getOutputStream().print("doGet:getRequestURI:" + request.getRequestURI() + "\n");
                response.getOutputStream().print("doGet:getRequestURL:" + request.getRequestURL() + "\n");
                response.getOutputStream().print("doGet:getPathInfo:" + request.getPathInfo() + "\n");
                AsyncContext asyncContext = request.startAsync(request, response);
                HttpServletRequest asyncRequest = (HttpServletRequest)asyncContext.getRequest();
                response.getOutputStream().print("doGet:async:getServletPath:" + asyncRequest.getServletPath() + "\n");
                response.getOutputStream().print("doGet:async:getRequestURI:" + asyncRequest.getRequestURI() + "\n");
                response.getOutputStream().print("doGet:async:getRequestURL:" + asyncRequest.getRequestURL() + "\n");
                response.getOutputStream().print("doGet:async:getPathInfo:" + asyncRequest.getPathInfo() + "\n");
                asyncContext.start(new AsyncRunnable(asyncContext));

            }
        }
