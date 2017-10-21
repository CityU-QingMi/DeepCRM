        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            try
            {
                _history.append(">H").append(_name);
                super.handle(target,baseRequest,request, response);
            }
            finally
            {
                _history.append("<H").append(_name);
            }
        }
