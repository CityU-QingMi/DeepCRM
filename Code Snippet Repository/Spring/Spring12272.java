	@Override
	public Resource transform(HttpServletRequest request, Resource resource) throws IOException {
		ResourceTransformer transformer = getNext();
		if (transformer == null) {
			return resource;
		}

		try {
			return transformer.transform(request, resource, this);
		}
		finally {
			this.index--;
		}
	}
