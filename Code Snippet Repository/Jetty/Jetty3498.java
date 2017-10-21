        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            byte[] buf = new byte[128 * 1024];
            for (int i = 0; i < buf.length; i++)
                buf[i] = (byte)("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_".charAt(i % 63));

            baseRequest.setHandled(true);
            response.setStatus(200);
            response.setContentType("text/plain");
            ServletOutputStream out = response.getOutputStream();
            long[] times = new long[10];
            for (int i = 0; i < times.length; i++)
            {
                // System.err.println("\nBLOCK "+request.getRequestURI()+" "+i);
                long start = System.currentTimeMillis();
                out.write(buf);
                long end = System.currentTimeMillis();
                times[i] = end - start;
                // System.err.println("Block "+request.getRequestURI()+" "+i+" "+times[i]);
            }
            out.println();
            for (long t : times)
            {
                out.print(t);
                out.print(",");
            }
            out.close();
        }
