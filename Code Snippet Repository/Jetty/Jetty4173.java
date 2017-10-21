        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            resp.setContentType("text/plain");
            resp.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = resp.getWriter();
            
            Object obj = req.getServletContext().getAttribute(DecoratedObjectFactory.ATTR);
            out.printf("Attribute[%s] = %s%n",DecoratedObjectFactory.ATTR,obj.getClass().getName());
            
            if (obj instanceof DecoratedObjectFactory)
            {
                out.printf("Object is a DecoratedObjectFactory%n");
                DecoratedObjectFactory objFactory = (DecoratedObjectFactory)obj;
                List<Decorator> decorators = objFactory.getDecorators();
                out.printf("Decorators.size = [%d]%n",decorators.size());
                for (Decorator decorator : decorators)
                {
                    out.printf(" decorator[] = %s%n",decorator.getClass().getName());
                }
            }
            else
            {
                out.printf("Object is NOT a DecoratedObjectFactory%n");
            }
        }
