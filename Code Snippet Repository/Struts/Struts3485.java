    @Inject
    public void setServletConfig(ServletContext servletContext) {
        if (!PlexusLifecycleListener.isLoaded() || !PlexusFilter.isLoaded()) {
            // uh oh! looks like the lifecycle listener wasn't installed. Let's inform the user
            String message = "********** FATAL ERROR STARTING UP PLEXUS-STRUTS INTEGRATION **********\n" +
                    "Looks like the Plexus listener was not configured for your web app! \n" +
                    "You need to add the following to web.xml: \n" +
                    "\n" +
                    "    <!-- this should be before the Struts filter -->\n" +
                    "    <filter>\n" +
                    "        <filter-name>plexus</filter-name>\n" +
                    "        <filter-class>org.apache.struts2.plexus.PlexusFilter</filter-class>\n" +
                    "    </filter>\n" +
                    "\n" +
                    "...\n" +
                    "\n" +
                    "    <!-- this should be before the Struts filter -->\n" +
                    "    <filter-mapping>\n" +
                    "        <filter-name>plexus</filter-name>\n" +
                    "        <url-pattern>/*</url-pattern>\n" +
                    "    </filter-mapping>\n" +
                    "\n" +
                    "...\n" +
                    "\n" +
                    "    <listener>\n" +
                    "        <listener-class>org.apache.struts2.plexus.PlexusLifecycleListener</listener-class>\n" +
                    "    </listener>";
            LOG.fatal(message);
            return;
        }

        base = (PlexusContainer) servletContext.getAttribute(PlexusLifecycleListener.KEY);
    }
