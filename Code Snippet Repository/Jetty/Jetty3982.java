        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            if (request.getDispatcherType() == DispatcherType.REQUEST)
            {
                request.startAsync(request, response);

                if (Boolean.valueOf(request.getParameter("dispatch")))
                {
                    request.getAsyncContext().dispatch();
                }

                if (Boolean.valueOf(request.getParameter("complete")))
                {
                    response.getOutputStream().write("completeBeforeThrow".getBytes());
                    if (Boolean.valueOf(request.getParameter("flush")))
                        response.flushBuffer();
                    request.getAsyncContext().complete();
                }

                throw new QuietServletException(new IOException("Test"));
            }
        }
