	@Override
	@SuppressWarnings("")
	public Mono<Void> write(Publisher<? extends Resource> inputStream, @Nullable ResolvableType actualType,
			ResolvableType elementType, @Nullable MediaType mediaType, ServerHttpRequest request,
			ServerHttpResponse response, Map<String, Object> hints) {

		HttpHeaders headers = response.getHeaders();
		headers.set(HttpHeaders.ACCEPT_RANGES, "bytes");

		List<HttpRange> ranges;
		try {
			ranges = request.getHeaders().getRange();
		}
		catch (IllegalArgumentException ex) {
			response.setStatusCode(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
			return response.setComplete();
		}

		return Mono.from(inputStream).flatMap(resource -> {

			if (ranges.isEmpty()) {
				return writeResource(resource, elementType, mediaType, response, hints);
			}

			response.setStatusCode(HttpStatus.PARTIAL_CONTENT);
			List<ResourceRegion> regions = HttpRange.toResourceRegions(ranges, resource);
			MediaType resourceMediaType = getResourceMediaType(mediaType, resource);

			if (regions.size() == 1){
				ResourceRegion region = regions.get(0);
				headers.setContentType(resourceMediaType);
				lengthOf(resource).ifPresent(length -> {
					long start = region.getPosition();
					long end = start + region.getCount() - 1;
					end = Math.min(end, length - 1);
					headers.add("Content-Range", "bytes " + start + '-' + end + '/' + length);
					headers.setContentLength(end - start + 1);
				});
				return writeSingleRegion(region, response);
			}
			else {
				String boundary = MimeTypeUtils.generateMultipartBoundaryString();
				MediaType multipartType = MediaType.parseMediaType("multipart/byteranges;boundary=" + boundary);
				headers.setContentType(multipartType);
				Map<String, Object> theHints = new HashMap<>(hints);
				theHints.put(ResourceRegionEncoder.BOUNDARY_STRING_HINT, boundary);
				return encodeAndWriteRegions(Flux.fromIterable(regions), resourceMediaType, response, theHints);
			}
		});
	}
