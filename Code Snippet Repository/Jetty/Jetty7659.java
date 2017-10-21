    public void contextInitialized(ServletContextEvent sce)
    {
        sce.getServletContext().setAttribute("com.acme.AnnotationTest.sclInjectTest", Boolean.valueOf(maxAmount != null));
        
        //Can't add a ServletContextListener from a ServletContextListener even if it is declared in web.xml
        try
        {
            sce.getServletContext().addListener(new NaughtyServletContextListener());
            sce.getServletContext().setAttribute("com.acme.AnnotationTest.sclFromSclRegoTest", Boolean.FALSE);
        }
        catch (IllegalArgumentException e)
        {
            sce.getServletContext().setAttribute("com.acme.AnnotationTest.sclFromSclRegoTest", Boolean.TRUE);
        }
        catch (Exception e)
        {
            sce.getServletContext().setAttribute("com.acme.AnnotationTest.sclFromSclRegoTest", Boolean.FALSE);
        }
        
        
        //Can't add an EventListener not part of the specified list for addListener()
        try
        {
            sce.getServletContext().addListener(new InvalidListener());
            sce.getServletContext().setAttribute("com.acme.AnnotationTest.invalidListenerRegoTest", Boolean.FALSE);
        }
        catch (IllegalArgumentException e)
        {
            sce.getServletContext().setAttribute("com.acme.AnnotationTest.invalidListenerRegoTest", Boolean.TRUE);
        }
        catch (Exception e)
        {
            sce.getServletContext().setAttribute("com.acme.AnnotationTest.invalidListenerRegoTest", Boolean.FALSE);
        } 
        
        //Programmatically add a listener and make sure its injected
        try
        {
            ValidListener l = sce.getServletContext().createListener(ValidListener.class);
            sce.getServletContext().setAttribute("com.acme.AnnotationTest.programListenerInjectTest", Boolean.valueOf(l != null && l.maxAmount != null));
        }   
        catch (Exception e)
        {
            sce.getServletContext().setAttribute("com.acme.AnnotationTest.programListenerInjectTest", Boolean.FALSE);
        }
    }
