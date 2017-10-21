        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            try
            {
                baseRequest.setHandled(true);
                response.setStatus(200);
                response.setHeader("test","value");

                OutputStream out=response.getOutputStream();

                String data = "Now is the time for all good men to come to the aid of the party.\n";
                data+="How now brown cow.\n";
                data+="The quick brown fox jumped over the lazy dog.\n";
                // data=data+data+data+data+data+data+data+data+data+data+data+data+data;
                // data=data+data+data+data+data+data+data+data+data+data+data+data+data;
                data=data+data+data+data;
                byte[] bytes=data.getBytes(StandardCharsets.UTF_8);

                for (int i=0;i<2;i++)
                {
                    // System.err.println("Write "+i+" "+bytes.length);
                    out.write(bytes);
                }
            }
            catch(Throwable e)
            {
                e.printStackTrace();
                throw new ServletException(e);
            }
        }
