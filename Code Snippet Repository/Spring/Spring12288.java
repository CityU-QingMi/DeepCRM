	protected void setHeaders(HttpServletResponse response, Resource resource, @Nullable MediaType mediaType)
			throws IOException {

		long length = resource.contentLength();
		if (length > Integer.MAX_VALUE) {
			response.setContentLengthLong(length);
		}
		else {
			response.setContentLength((int) length);
		}

		if (mediaType != null) {
			response.setContentType(mediaType.toString());
		}
		if (resource instanceof HttpResource) {
			HttpHeaders resourceHeaders = ((HttpResource) resource).getResponseHeaders();
			resourceHeaders.toSingleValueMap().entrySet()
					.stream().forEach(entry -> response.setHeader(entry.getKey(), entry.getValue()));
		}
		response.setHeader(HttpHeaders.ACCEPT_RANGES, "bytes");
	}
