	protected List<InterceptorMapping> build(InterceptorRef[] interceptors, String actionName, PackageConfig.Builder builder) {
	    List<InterceptorMapping> interceptorList = new ArrayList<>(10);
	    for (InterceptorRef interceptor : interceptors) {
            LOG.trace("Adding interceptor [{}] to [{}]", interceptor.value(), actionName);
            Map<String, String> params = StringTools.createParameterMap(interceptor.params());
            interceptorList.addAll(buildInterceptorList(builder, interceptor, params));
        }

	    return interceptorList;
	}
