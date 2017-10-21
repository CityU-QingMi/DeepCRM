	public CachePutOperation(
			CacheMethodDetails<CachePut> methodDetails, CacheResolver cacheResolver, KeyGenerator keyGenerator) {

		super(methodDetails, cacheResolver, keyGenerator);
		CachePut ann = methodDetails.getCacheAnnotation();
		this.exceptionTypeFilter = createExceptionTypeFilter(ann.cacheFor(), ann.noCacheFor());
		this.valueParameterDetail = initializeValueParameterDetail(methodDetails.getMethod(), this.allParameterDetails);
		if (this.valueParameterDetail == null) {
			throw new IllegalArgumentException("No parameter annotated with @CacheValue was found for " +
					"" + methodDetails.getMethod());
		}
	}
