        @Override
        protected void doGet(HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException
        {
            DispatcherType dispatcherType = request.getDispatcherType();
            response.getOutputStream().print("doGet." + dispatcherType.name() + ".requestURI:" + request.getRequestURI() + "\n");

            if (dispatcherType == DispatcherType.ASYNC)
            {
                response.getOutputStream().print("Dispatched back to " + SelfDispatchingServlet.class.getSimpleName() + "\n");
            }
            else
            {
                final AsyncContext asyncContext = request.startAsync(request, response);
                new Thread(() -> asyncContext.dispatch()).start();
            }
        }
