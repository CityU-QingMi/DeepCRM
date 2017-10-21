        @Override
        public void doNonErrorHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            baseRequest.setHandled(true);
            response.setBufferSize(8);
            response.getWriter().write("fo");
            response.getWriter().write("ob");
            response.getWriter().write("ar");
            response.getWriter().write("fo");
            response.getWriter().write("ob");
            response.getWriter().write("ar");
            super.doNonErrorHandle(target, baseRequest, request, response);
        }
