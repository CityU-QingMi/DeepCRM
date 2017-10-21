	protected TransformerFactory newTransformerFactory(@Nullable Class<? extends TransformerFactory> transformerFactoryClass) {
		if (transformerFactoryClass != null) {
			try {
				return ReflectionUtils.accessibleConstructor(transformerFactoryClass).newInstance();
			}
			catch (Exception ex) {
				throw new TransformerFactoryConfigurationError(ex, "Could not instantiate TransformerFactory");
			}
		}
		else {
			return TransformerFactory.newInstance();
		}
	}
