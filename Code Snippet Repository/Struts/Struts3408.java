    protected TemplateLoader createTemplateLoader(ServletContext servletContext, String templatePath) {
        TemplateLoader templatePathLoader = null;

        try {
             if (templatePath.startsWith("class://")) {
                 // substring(7) is intentional as we "reuse" the last slash
                 templatePathLoader = new ClassTemplateLoader(getClass(), templatePath.substring(7));
             } else if (templatePath.startsWith("file://")) {
                 templatePathLoader = new FileTemplateLoader(new File(URI.create(templatePath)));
             }
         } catch (IOException e) {
             LOG.error("Invalid template path specified: " + e.getMessage(), e);
         }

        // presume that most apps will require the class and webapp template loader
        // if people wish to
        return templatePathLoader != null ?
                new MultiTemplateLoader(new TemplateLoader[]{
                        templatePathLoader,
                        new WebappTemplateLoader(servletContext),
                        new StrutsClassTemplateLoader(),
                        new FreeMarkerBundleResourceLoader()
                })
                : new MultiTemplateLoader(new TemplateLoader[]{
                new WebappTemplateLoader(servletContext),
                new StrutsClassTemplateLoader(),
                new FreeMarkerBundleResourceLoader()
        });
    }
