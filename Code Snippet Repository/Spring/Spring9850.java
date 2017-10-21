		@Override
		public URI build(Map<String, ?> uriVars) {
			if (!defaultUriVariables.isEmpty()) {
				Map<String, Object> map = new HashMap<>();
				map.putAll(defaultUriVariables);
				map.putAll(uriVars);
				uriVars = map;
			}
			if (encodingMode.equals(EncodingMode.VALUES_ONLY)) {
				uriVars = UriUtils.encodeUriVariables(uriVars);
			}
			UriComponents uriComponents = this.uriComponentsBuilder.build().expand(uriVars);
			if (encodingMode.equals(EncodingMode.URI_COMPONENT)) {
				uriComponents = uriComponents.encode();
			}
			return URI.create(uriComponents.toString());
		}
