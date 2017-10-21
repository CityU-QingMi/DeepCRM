        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException
        {
            String action = request.getParameter("action");
            if ("init".equals(action))
            {
                HttpSession session = request.getSession(true);
                session.setAttribute("test", "test");
            }
            else if ("change".equals(action))
            {
                String tmp = request.getParameter("val");
                int val = (StringUtil.isBlank(tmp)?0:Integer.valueOf(tmp.trim()));
                HttpSession session = request.getSession(false);
                assertNotNull(session);
                session.setMaxInactiveInterval(val);
            }
            else if ("check".equals(action))
            {
                HttpSession session = request.getSession(false);
                assertNotNull(session);
            }
        }
