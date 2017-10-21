        @Override
        protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            String method = request.getMethod().toUpperCase(Locale.ENGLISH);
            switch (method)
            {
                case "GET":
                {
                    int contentLength = request.getIntHeader("X-Download");
                    if (contentLength > 0)
                        response.getOutputStream().write(new byte[contentLength]);
                    break;
                }
                case "POST":
                {
                    int content_length=request.getContentLength();
                    ByteArrayOutputStream2 bout = new ByteArrayOutputStream2(content_length>0?content_length:16*1024);
                    IO.copy(request.getInputStream(), bout);
                    response.getOutputStream().write(bout.getBuf(),0,bout.getCount());
                    break;
                }
            }
        }
