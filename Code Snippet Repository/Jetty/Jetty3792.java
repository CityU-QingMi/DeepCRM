        @Override
        public void doHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            try
            {
                _history.append(">W").append(_name);
                super.nextHandle(target,baseRequest,request,response);
            }
            finally
            {
                _history.append("<W").append(_name);
            }
        }
