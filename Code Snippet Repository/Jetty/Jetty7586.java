    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException
    {
        try
        {
            String action = request.getParameter("action");
            if ("set".equals(action))
            {
                HttpSession session = request.getSession(true);
                session.setAttribute("staticAttribute", new TestSharedStatic());

                Object staticAttribute = session.getAttribute("staticAttribute");
                Assert.assertTrue(staticAttribute instanceof TestSharedStatic);
                
//                session.setAttribute("objectAttribute", new TestSharedNonStatic());
           
                // The session itself is not shareable, since the implementation class
                // refers to the session manager via the hidden field this$0, and
                // it seems there is no way to mark the hidden field as transient.
//                session.setAttribute("sessionAttribute", session);
            }
            else if ("get".equals(action))
            {
                HttpSession session = request.getSession(false);
                Object staticAttribute = session.getAttribute("staticAttribute");
                Assert.assertTrue(staticAttribute instanceof TestSharedStatic);
                
//                Object objectAttribute = session.getAttribute("objectAttribute");
//                Assert.assertTrue(objectAttribute instanceof TestSharedNonStatic);
                
//                Object sessionAttribute = session.getAttribute("sessionAttribute");
//                assertTrue(sessionAttribute instanceof HttpSession);
            }
        }
        catch (Exception e)
        {
            // e.printStackTrace();
            httpServletResponse.sendError(500,e.toString());
            throw new ServletException(e);
        }
    }
