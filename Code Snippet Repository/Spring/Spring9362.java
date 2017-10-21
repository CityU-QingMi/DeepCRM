	protected void writeResourceRegion(ResourceRegion region, HttpOutputMessage outputMessage) throws IOException {
		Assert.notNull(region, "ResourceRegion must not be null");
		HttpHeaders responseHeaders = outputMessage.getHeaders();

		long start = region.getPosition();
		long end = start + region.getCount() - 1;
		Long resourceLength = region.getResource().contentLength();
		end = Math.min(end, resourceLength - 1);
		long rangeLength = end - start + 1;
		responseHeaders.add("Content-Range", "bytes " + start + '-' + end + '/' + resourceLength);
		responseHeaders.setContentLength(rangeLength);

		InputStream in = region.getResource().getInputStream();
		try {
			StreamUtils.copyRange(in, outputMessage.getBody(), start, end);
		}
		finally {
			try {
				in.close();
			}
			catch (IOException ex) {
				// ignore
			}
		}
	}
