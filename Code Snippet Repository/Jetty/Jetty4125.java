        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            response.sendError(599);
            // The below should result in no operation, as response should be closed.
            try
            {
                response.setStatus(200); // this status code should not be seen
                response.getWriter().append("This shouldn't be seen");
            }
            catch (Throwable ignore)
            {
            }
        }
