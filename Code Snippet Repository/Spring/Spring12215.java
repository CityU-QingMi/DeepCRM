	public RequestResponseBodyAdviceChain(@Nullable List<Object> requestResponseBodyAdvice) {
		if (requestResponseBodyAdvice != null) {
			for (Object advice : requestResponseBodyAdvice) {
				Class<?> beanType = (advice instanceof ControllerAdviceBean ?
						((ControllerAdviceBean) advice).getBeanType() : advice.getClass());
				if (beanType != null) {
					if (RequestBodyAdvice.class.isAssignableFrom(beanType)) {
						this.requestBodyAdvice.add(advice);
					}
					else if (ResponseBodyAdvice.class.isAssignableFrom(beanType)) {
						this.responseBodyAdvice.add(advice);
					}
				}
			}
		}
	}
