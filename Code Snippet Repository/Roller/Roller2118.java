    public void init(Map initData) throws WebloggerException {

        if (!WebloggerConfig.getBooleanProperty("planet.aggregator.enabled")) {
            return;
        }
        
        // we expect the init data to contain a weblogRequest object
        this.weblogRequest = (WeblogRequest) initData.get("parsedRequest");
        if(this.weblogRequest == null) {
            throw new WebloggerException("expected weblogRequest from init data");
        }
        
        if (weblogRequest instanceof WeblogPageRequest) {
            ThemeTemplate weblogPage = ((WeblogPageRequest)weblogRequest).getWeblogPage();
            pageLink = (weblogPage != null) ? weblogPage.getLink() : null;
            pageNum = ((WeblogPageRequest)weblogRequest).getPageNum();
        }  
        
        // look for url strategy
        urlStrategy = (URLStrategy) initData.get("urlStrategy");
        if(urlStrategy == null) {
            urlStrategy = WebloggerFactory.getWeblogger().getUrlStrategy();
        }
        
        planetUrlStrategy = WebloggerFactory.getWeblogger().getPlanetURLStrategy();
        
        // extract weblog object
        weblog = weblogRequest.getWeblog();
    } 
