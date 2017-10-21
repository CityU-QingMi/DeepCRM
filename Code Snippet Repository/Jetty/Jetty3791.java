        @Override
        public void doScope(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            try
            {
                _history.append(">S").append(_name);
                super.nextScope(target,baseRequest,request, response);
            }
            finally
            {
                _history.append("<S").append(_name);
            }
        }
