    public void init(ServletConfig servletConfig) throws ServletException {

        super.init(servletConfig);

        log.info("Initializing FeedServlet");

        // get a reference to the weblog feed cache
        this.weblogFeedCache = WeblogFeedCache.getInstance();

        // get a reference to the site wide cache
        this.siteWideCache = SiteWideCache.getInstance();
    }
