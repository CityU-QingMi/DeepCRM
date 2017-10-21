        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            try
            {
                _requestValidator.validate(request);
            }
            catch (Error e)
            {
                _error = e;
            }
            catch (Throwable e)
            {
                _error = new Error(e);
            }
        }
