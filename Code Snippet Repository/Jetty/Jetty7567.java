        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            HttpSession session = request.getSession(false);

            String action = request.getParameter("action");
            if ("set".equals(action))
            {
                if (session == null) session = request.getSession(true);
                int value = Integer.parseInt(request.getParameter("value"));
                session.setAttribute("value", value);
                PrintWriter writer = response.getWriter();
                writer.println(value);
                writer.flush();
            }
            else if ("get".equals(action))
            {
                int value = (Integer)session.getAttribute("value");
                int x = session.getMaxInactiveInterval();
                assertTrue(x > 0);
                PrintWriter writer = response.getWriter();
                writer.println(value);
                writer.flush();
            }
        }
