	public SessionAttributesHandler(Class<?> handlerType, SessionAttributeStore sessionAttributeStore) {
		Assert.notNull(sessionAttributeStore, "SessionAttributeStore may not be null");
		this.sessionAttributeStore = sessionAttributeStore;

		SessionAttributes annotation =
				AnnotatedElementUtils.findMergedAnnotation(handlerType, SessionAttributes.class);
		if (annotation != null) {
			this.attributeNames.addAll(Arrays.asList(annotation.names()));
			this.attributeTypes.addAll(Arrays.asList(annotation.types()));
		}
		this.knownAttributeNames.addAll(this.attributeNames);
	}
