		@Override
		public HttpHeaders getResponseHeaders() {
			HttpHeaders headers;
			if(this.original instanceof HttpResource) {
				headers = ((HttpResource) this.original).getResponseHeaders();
			}
			else {
				headers = new HttpHeaders();
			}
			headers.setETag("\"" + this.version + "\"");
			return headers;
		}
