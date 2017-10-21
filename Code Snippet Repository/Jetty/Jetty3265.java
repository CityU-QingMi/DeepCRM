        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            baseRequest.setHandled(true);
            response.setStatus(200);
            OutputStream out = response.getOutputStream();

            for (int i=0;i<20;i++)
            {
                out.write("Hello World\r\n".getBytes());
                out.flush();
                try{Thread.sleep(50);}catch(Exception e){e.printStackTrace();}
            }
            out.close();
        }
