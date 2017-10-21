	private byte[] getContentRangeHeader(ResourceRegion region) {
		long start = region.getPosition();
		long end = start + region.getCount() - 1;
		OptionalLong contentLength = contentLength(region.getResource());
		if (contentLength.isPresent()) {
			return getAsciiBytes("Content-Range: bytes " + start + '-' + end + '/' + contentLength.getAsLong() + "\r\n\r\n");
		}
		else {
			return getAsciiBytes("Content-Range: bytes " + start + '-' + end + "\r\n\r\n");
		}
	}
