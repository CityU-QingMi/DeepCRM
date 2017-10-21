	public ResourceRegion toResourceRegion(Resource resource) {
		// Don't try to determine contentLength on InputStreamResource - cannot be read afterwards...
		// Note: custom InputStreamResource subclasses could provide a pre-calculated content length!
		Assert.isTrue(resource.getClass() != InputStreamResource.class,
				"Cannot convert an InputStreamResource to a ResourceRegion");
		try {
			long contentLength = resource.contentLength();
			Assert.isTrue(contentLength > 0, "Resource content length should be > 0");
			long start = getRangeStart(contentLength);
			long end = getRangeEnd(contentLength);
			return new ResourceRegion(resource, start, end - start + 1);
		}
		catch (IOException ex) {
			throw new IllegalArgumentException("Failed to convert Resource to ResourceRegion", ex);
		}
	}
