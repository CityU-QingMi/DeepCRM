        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            baseRequest.setHandled(true);
            response.setStatus(200);
            OutputStream out = response.getOutputStream();
            byte[] buffer = new byte[128*1024*1024];
            Arrays.fill(buffer,(byte)'x');
            for (int i=0;i<128*1024;i++)
            {
                buffer[i*1024+1022]='\r';
                buffer[i*1024+1023]='\n';
            }
            ByteBuffer bb = ByteBuffer.wrap(buffer);
            ((HttpOutput)out).sendContent(bb);
            out.close();
        }
