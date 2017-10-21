        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            baseRequest.setHandled(true);
            
            if (_bufferSize>0)
                response.setBufferSize(_bufferSize);
            if (_mimeType!=null)
                response.setContentType(_mimeType);

            if (_reset)
            {
                response.getOutputStream().print("THIS WILL BE RESET");
                response.getOutputStream().flush();
                response.getOutputStream().print("THIS WILL BE RESET");
                response.resetBuffer();
            }
            for (int i=0;i<_writes;i++)
            {
                response.addHeader("Write",Integer.toString(i));
                response.getOutputStream().write(_content);
                if (_flush)
                    response.getOutputStream().flush();
            }

            if (_close)
                response.getOutputStream().close();
            response.addHeader("Written","true");
        }  
