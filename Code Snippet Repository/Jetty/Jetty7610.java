        @Override
        protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
        {
            String action = request.getParameter( "action" );
            if ( "init".equals( action ) )
            {
                HttpSession session = request.getSession( true );
                session.setAttribute( "value", 0 );
            }
            else if ( "increment".equals( action ) )
            {
                HttpSession session = request.getSession( false );
                assertNotNull(session);
                synchronized(session)
                {
                    int value = (Integer) session.getAttribute( "value" );
                    session.setAttribute( "value", value + 1 );
                }
            }
            else if ( "result".equals( action ) )
            {
                HttpSession session = request.getSession( false );
                assertNotNull(session);
                Integer value = null;
                synchronized (session)
                {
                    value = (Integer) session.getAttribute( "value" );
                }
                PrintWriter writer = response.getWriter();
                writer.println( value );
                writer.flush();
            }
        }
