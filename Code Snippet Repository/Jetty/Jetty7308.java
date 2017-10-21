        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            baseRequest.setHandled(true);
            ServletInputStream input = request.getInputStream();
            while (true)
            {
                int read = input.read();
                if (read < 0)
                    break;
            }
            response.setStatus(HttpStatus.OK_200);
        }
