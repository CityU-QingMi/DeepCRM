    public ParsedRequest(HttpServletRequest request) throws InvalidRequestException {
        
        // keep a reference to the original request
        this.request = request;
        
        // login status
        java.security.Principal prince = request.getUserPrincipal();
        if(prince != null) {
            this.authenticUser = prince.getName();
        }
        // set the detected type of the request
        deviceType = MobileDeviceRepository.getRequestType(request);
    }
