        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException
        {
            String action = request.getParameter("action");
            if ("init".equals(action))
            {
                HttpSession session = request.getSession(true);
                assertNotNull(session);
                String tmp = request.getParameter("val");
                int val = (StringUtil.isBlank(tmp)?0:Integer.valueOf(tmp.trim()));
                session.setMaxInactiveInterval(val);
            }
            else if ("change".equals(action))
            {
                String tmp = request.getParameter("val");
                int val = (StringUtil.isBlank(tmp)?0:Integer.valueOf(tmp.trim()));
                HttpSession session = request.getSession(false);
                assertNotNull(session);
                session.setMaxInactiveInterval(val);
            }
        }
