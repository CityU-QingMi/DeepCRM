        @Override
        protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException
        {
            if (request.getDispatcherType() == DispatcherType.ASYNC)
            {
                response.getOutputStream().print("Dispatched back to ForwardingServlet");
            }
            else
            {
                request.getRequestDispatcher("/dispatchingServlet").forward(request, response);
            }
        }
