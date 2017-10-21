		@Override
		public BodyBuilder eTag(String etag) {
			if (!etag.startsWith("\"") && !etag.startsWith("W/\"")) {
				etag = "\"" + etag;
			}
			if (!etag.endsWith("\"")) {
				etag = etag + "\"";
			}
			this.headers.setETag(etag);
			return this;
		}
