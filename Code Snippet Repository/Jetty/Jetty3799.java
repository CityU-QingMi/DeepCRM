        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            if (!"/".equals(target))
            {
                response.sendError(404);
                return;
            }

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head><title>Contexts</title></head>");
            out.println("<body>");
            out.println("<h4>Child Contexts</h4>");
            out.println("<ul>");
            for (String child : childContexts)
            {
                out.printf("<li><a href=\"%s\">%s</a></li>%n",child,child);
            }
            out.println("</ul>");
            out.println("</body></html>");
            baseRequest.setHandled(true);
        }
