	private void writeResourceRegionCollection(Collection<ResourceRegion> resourceRegions,
			HttpOutputMessage outputMessage) throws IOException {

		Assert.notNull(resourceRegions, "Collection of ResourceRegion should not be null");
		HttpHeaders responseHeaders = outputMessage.getHeaders();

		MediaType contentType = responseHeaders.getContentType();
		String boundaryString = MimeTypeUtils.generateMultipartBoundaryString();
		responseHeaders.set(HttpHeaders.CONTENT_TYPE, "multipart/byteranges; boundary=" + boundaryString);
		OutputStream out = outputMessage.getBody();

		for (ResourceRegion region : resourceRegions) {
			long start = region.getPosition();
			long end = start + region.getCount() - 1;
			InputStream in = region.getResource().getInputStream();
			try {
				// Writing MIME header.
				println(out);
				print(out, "--" + boundaryString);
				println(out);
				if (contentType != null) {
					print(out, "Content-Type: " + contentType.toString());
					println(out);
				}
				Long resourceLength = region.getResource().contentLength();
				end = Math.min(end, resourceLength - 1);
				print(out, "Content-Range: bytes " + start + '-' + end + '/' + resourceLength);
				println(out);
				println(out);
				// Printing content
				StreamUtils.copyRange(in, out, start, end);
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

		println(out);
		print(out, "--" + boundaryString + "--");
	}
