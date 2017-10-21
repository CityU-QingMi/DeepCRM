	@Override
	@Nullable
	public String resolveUrlPath(String resourcePath, List<? extends Resource> locations) {
		ResourceResolver resolver = getNext();
		if (resolver == null) {
			return null;
		}

		try {
			return resolver.resolveUrlPath(resourcePath, locations, this);
		}
		finally {
			this.index--;
		}
	}
