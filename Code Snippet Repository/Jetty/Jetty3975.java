        @Override
        protected void doGet(HttpServletRequest req, final HttpServletResponse response) throws ServletException, IOException
        {
            Request request = (Request)req;
            if (request.getDispatcherType() == DispatcherType.ASYNC)
            {
                response.getOutputStream().print("Dispatched back to AsyncDispatchingServlet");
            }
            else
            {
                boolean wrapped = false;
                final AsyncContext asyncContext;
                if (request.getParameter("dispatchRequestResponse") != null)
                {
                    wrapped = true;
                    asyncContext = request.startAsync(request, new Wrapper(response));
                }
                else
                {
                    asyncContext = request.startAsync();
                }

                new Thread(new DispatchingRunnable(asyncContext, wrapped)).start();
            }
        }
