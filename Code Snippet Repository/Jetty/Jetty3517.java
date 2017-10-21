        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            baseRequest.setHandled(true);

            if (request.getContentType()!=null)
                response.setContentType(request.getContentType());
            if (request.getParameter("charset")!=null)
                response.setCharacterEncoding(request.getParameter("charset"));
            else if (request.getCharacterEncoding()!=null)
                response.setCharacterEncoding(request.getCharacterEncoding());

            PrintWriter writer=response.getWriter();

            int count=0;
            BufferedReader reader=request.getReader();

            if (request.getContentLength()!=0)
            {
                String line=reader.readLine();
                while (line!=null)
                {
                    writer.print(line);
                    writer.print("\n");
                    count+=line.length();
                    line=reader.readLine();
                }
            }

            if (count==0)
            {
                if (_musthavecontent)
                    throw new IllegalStateException("no input recieved");

                writer.println("No content");
            }

            // just to be difficult
            reader.close();
            writer.close();

            if (reader.read()>=0)
                throw new IllegalStateException("Not closed");
        }
