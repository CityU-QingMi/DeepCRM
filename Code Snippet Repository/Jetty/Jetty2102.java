    public void fixJspFactory ()
    {   
        try
        {
            Class<javax.servlet.ServletContext> servletContextClass = javax.servlet.ServletContext.class;
            // bug #299733
            JspFactory fact = JspFactory.getDefaultFactory();
            if (fact == null)
            { // bug #299733
              // JspFactory does a simple
              // Class.getForName("org.apache.jasper.runtime.JspFactoryImpl")
              // however its bundles does not import the jasper package
              // so it fails. let's help things out:
                fact = (JspFactory) JettyBootstrapActivator.class.getClassLoader().loadClass(DEFAULT_JSP_FACTORY_IMPL_CLASS).newInstance();
                JspFactory.setDefaultFactory(fact);
            }
        }
        catch (Exception e)
        {
            LOG.warn("Unable to set the JspFactory: jsp support incomplete.", e);
        }
    }
