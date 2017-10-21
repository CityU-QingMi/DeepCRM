        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            if (req.getRequestURI().endsWith("/index.html"))
            {
                resp.sendRedirect("http://localhost:" + req.getLocalPort() + req.getContextPath() + req.getServletPath() + "/other.html?secret=pipo+molo");
            }
            else
            {
                resp.setContentType("text/plain");
                if ("pipo molo".equals(req.getParameter("secret")))
                    resp.getWriter().println("success");
            }
        }
