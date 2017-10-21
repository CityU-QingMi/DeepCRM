        @Override
        public void handle(String path, final Request request, HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws IOException, ServletException
        {
            httpResponse.setStatus(200);
            request.setHandled(true);

            BufferedReader in = request.getReader();
            PrintWriter out =httpResponse.getWriter();
            int read=Integer.valueOf(request.getParameter("read"));
            // System.err.println("read="+read);
            for (int i=read;i-->0;)
            {
                int c=in.read();
                // System.err.println("in="+c);
                if (c<0)
                    break;
                out.write(c);
            }
            out.write('\n');
        }
