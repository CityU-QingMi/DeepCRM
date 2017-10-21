    public Servlet getServlet()
        throws ServletException, IOException, FileNotFoundException
    {
        if (reload) {
            synchronized (this) {
                // Synchronizing on jsw enables simultaneous loading
                // of different pages, but not the same page.
                if (reload) {
                    // This is to maintain the original protocol.
                    destroy();
                    
                    Servlet servlet = null;
                    
                    try {
                        servletClass = ctxt.load();
                        servlet = (Servlet) servletClass.newInstance();
                        AnnotationProcessor annotationProcessor = (AnnotationProcessor) config.getServletContext().getAttribute(AnnotationProcessor.class.getName());
                        if (annotationProcessor != null) {
                           annotationProcessor.processAnnotations(servlet);
                           annotationProcessor.postConstruct(servlet);
                        }
                    } catch (IllegalAccessException e) {
                        throw new JasperException(e);
                    } catch (InstantiationException e) {
                        throw new JasperException(e);
                    } catch (Exception e) {
                        throw new JasperException(e);
                    }
                    
                    servlet.init(config);

                    if (!firstTime) {
                        ctxt.getRuntimeContext().incrementJspReloadCount();
                    }

                    theServlet = servlet;
                    reload = false;
                }
            }    
        }
        return theServlet;
    }
