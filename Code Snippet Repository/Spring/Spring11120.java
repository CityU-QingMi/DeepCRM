		public BodyInserterRequest(HttpMethod method, URI url, HttpHeaders headers,
				MultiValueMap<String, String> cookies,
				BodyInserter<?, ? super ClientHttpRequest> inserter,
				Map<String, Object> attributes) {

			this.method = method;
			this.url = url;
			this.headers = HttpHeaders.readOnlyHttpHeaders(headers);
			this.cookies = CollectionUtils.unmodifiableMultiValueMap(cookies);
			this.inserter = inserter;
			this.attributes = Collections.unmodifiableMap(attributes);
		}
