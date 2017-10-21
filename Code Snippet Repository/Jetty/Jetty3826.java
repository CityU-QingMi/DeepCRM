        public void handle(String path, Request request, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, ServletException
        {
            request.setHandled(true);

            InputStream in = request.getInputStream();
            int total = 0;
            byte[] b = new byte[1024 * 1024];
            int read;
            while ((read = in.read(b)) >= 0)
                total += read;
//            System.err.println("Read " + total + " request bytes");
            httpResponse.getOutputStream().write(String.valueOf(total).getBytes());
        }
