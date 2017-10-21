        @Override
        public void handle(String path, Request request, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, ServletException
        {
            request.setHandled(true);
            InputStream in = request.getInputStream();
            byte[] b = new byte[4096*4];
            int read;
            while((read = in.read(b))>=0)
                total += read;
            System.err.println("Read "+ total);
        }
