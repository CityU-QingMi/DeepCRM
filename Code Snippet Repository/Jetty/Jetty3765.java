        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException
        {
            if (baseRequest.isHandled() || response.isCommitted())
            {
                return;
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/errorok/");
            assertThat("Dispatcher", dispatcher, notNullValue());

            try
            {
                dispatcher.forward(request,response);
            }
            catch (ServletException e)
            {
                throw new IOException("Dispatch.forward failed",e);
            }
        }
