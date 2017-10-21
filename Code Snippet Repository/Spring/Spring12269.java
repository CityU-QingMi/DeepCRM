	@Override
	@Nullable
	public Resource resolveResource(
			@Nullable HttpServletRequest request, String requestPath, List<? extends Resource> locations) {

		ResourceResolver resolver = getNext();
		if (resolver == null) {
			return null;
		}

		try {
			return resolver.resolveResource(request, requestPath, locations, this);
		}
		finally {
			this.index--;
		}
	}
