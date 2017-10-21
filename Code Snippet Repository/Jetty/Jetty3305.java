        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            ((Request)request).setHandled(true);

            if (request.getContentLength()>0
                    && !MimeTypes.Type.FORM_ENCODED.asString().equals(request.getContentType())
                    && !request.getContentType().startsWith("multipart/form-data"))
                _content=IO.toString(request.getInputStream());

            if (_checker!=null && _checker.check(request,response))
                response.setStatus(200);
            else
                response.sendError(500);
        }
