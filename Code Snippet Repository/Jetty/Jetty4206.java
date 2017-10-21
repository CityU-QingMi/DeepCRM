        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            // collect error details
            String reason = (response instanceof Response)?((Response)response).getReason():null;
            int status = response.getStatus();

            // intentionally set response status to OK (this is a test to see what is actually logged)
            response.setStatus(200);
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.printf("Error %d: %s%n",status,reason);
        }
