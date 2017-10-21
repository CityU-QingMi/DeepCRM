        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            // System.err.println("HANDLE "+request.getRequestURI());
            String ssl_id = (String)request.getAttribute("javax.servlet.request.ssl_session_id");
            assertNotNull(ssl_id);
            
            if (request.getParameter("dump")!=null)
            {
                ServletOutputStream out=response.getOutputStream();
                byte[] buf = new byte[Integer.valueOf(request.getParameter("dump"))];
                // System.err.println("DUMP "+buf.length);
                for (int i=0;i<buf.length;i++)
                    buf[i]=(byte)('0'+(i%10));
                out.write(buf);
                out.close();
            }
            else
            {
                PrintWriter out=response.getWriter();
                out.print(HELLO_WORLD);
                out.close();
            }
        }
