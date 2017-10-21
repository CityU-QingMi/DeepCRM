        public void handle(String target, Request request, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, ServletException
        {
            request.setHandled(true);

            String uri = httpRequest.getRequestURI();
            switch (uri)
            {
                case "/echo":
                {
                    StringBuilder builder = new StringBuilder();
                    builder.append(httpRequest.getMethod()).append(" ").append(uri);
                    if (httpRequest.getQueryString() != null)
                        builder.append("?").append(httpRequest.getQueryString());

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    InputStream input = httpRequest.getInputStream();
                    int read;
                    while ((read = input.read()) >= 0)
                        baos.write(read);
                    baos.close();
                    byte[] bytes = baos.toByteArray();

                    ServletOutputStream output = httpResponse.getOutputStream();
                    if (bytes.length == 0)
                        output.print(builder.toString());
                    else
                        output.println(builder.toString());
                    output.write(bytes);
                    break;
                }
                case "/close":
                {
                    request.getHttpChannel().getEndPoint().close();
                    break;
                }
                default:
                {
                    throw new ServletException();
                }
            }
        }
