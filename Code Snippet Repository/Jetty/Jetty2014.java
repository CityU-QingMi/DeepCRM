        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            String arg = req.getParameter("action");
            if (arg == null)
                return;
            HttpSession s = null;
            if ("set".equals(arg))
            {
                s = req.getSession(true);
                assertNotNull(s);
                s.setAttribute("val", req.getParameter("value"));
            }
            else if ("get".equals(arg))
            {
                s = req.getSession(false);
                System.err.println("GET: s="+s);
            }
            else if ("del".equals(arg))
            {
                s = req.getSession();
                assertNotNull(s);
                s.invalidate();
                s = null;
            }
            
            resp.setContentType("text/html");
            PrintWriter w = resp.getWriter();
            if (s == null)
                w.write("No session");
            else
                w.write((String)s.getAttribute("val"));
        }
