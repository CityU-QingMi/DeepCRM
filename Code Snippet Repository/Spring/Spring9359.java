	@Override
	@SuppressWarnings("")
	protected MediaType getDefaultContentType(Object object) {
		Resource resource = null;
		if (object instanceof ResourceRegion) {
			resource = ((ResourceRegion) object).getResource();
		}
		else {
			Collection<ResourceRegion> regions = (Collection<ResourceRegion>) object;
			if (!regions.isEmpty()) {
				resource = regions.iterator().next().getResource();
			}
		}
		return MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM);
	}
