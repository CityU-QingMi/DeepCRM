		@Override
		public HttpHeaders getResponseHeaders() {
			HttpHeaders headers;
			if(this.original instanceof HttpResource) {
				headers = ((HttpResource) this.original).getResponseHeaders();
			}
			else {
				headers = new HttpHeaders();
			}
			headers.add(HttpHeaders.CONTENT_ENCODING, "gzip");
			return headers;
		}
