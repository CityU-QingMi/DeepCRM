    public void init(Map initData) throws WebloggerException {      
        
        // we expect the init data to contain a parsedRequest object
        parsedRequest = (ParsedRequest) initData.get("parsedRequest");
        if(parsedRequest == null) {
            throw new WebloggerException("expected parsedRequest from init data");
        }
        
        // extract weblog object if possible
        if(parsedRequest instanceof WeblogRequest) {
            WeblogRequest weblogRequest = (WeblogRequest) parsedRequest;
            weblog = weblogRequest.getWeblog();
        }
    }
