        @Override
        public void contextInitialized(ServletContextEvent sce)
        {
            //Can add a ServletContextListener from a ServletContainerInitializer
            sce.getServletContext().setAttribute("com.acme.AnnotationTest.listenerTest", Boolean.TRUE);
            
            //Can't add a ServletContextListener from a ServletContextListener
            try
            {
                sce.getServletContext().addListener(new BarListener());
                sce.getServletContext().setAttribute("com.acme.AnnotationTest.listenerRegoTest", Boolean.FALSE);
            }
            catch (UnsupportedOperationException e)
            {
                sce.getServletContext().setAttribute("com.acme.AnnotationTest.listenerRegoTest", Boolean.TRUE);
            }
            catch (Exception e)
            {
                sce.getServletContext().setAttribute("com.acme.AnnotationTest.listenerRegoTest", Boolean.FALSE);
            }
        }
