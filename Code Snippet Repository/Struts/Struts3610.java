    public HttpHeaders options() {
    	
    	String methods = OPTIONS;
    	
    	Method[] meths = this.getClass().getDeclaredMethods();
    	for (Method m : meths) {
    		String methodName = m.getName();
    		if (!methods.contains(GET) &&
    				(methodName.equals("index")
    				|| methodName.equals("show")
    				|| methodName.equals("edit")
    				|| methodName.equals("editNew"))) {
    			methods += DIVIDER + GET;
    		} else if (methodName.equals("create")) {
    			methods += DIVIDER + POST;
    		} else if (methodName.equals("update")) {
    			methods += DIVIDER + PUT;
    		}else if (methodName.equals("destroy")) {
    			methods += DIVIDER + DELETE;
    		}
    	}
    	
    	HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response = ServletActionContext.getResponse();
    	response.addHeader("Allow", methods);
    	
    	DefaultHttpHeaders httpHeaders = new DefaultHttpHeaders();
    	httpHeaders.apply(request, response, this);
    	httpHeaders.disableCaching().withStatus(HttpServletResponse.SC_OK);
    	
    	return httpHeaders;
	}
