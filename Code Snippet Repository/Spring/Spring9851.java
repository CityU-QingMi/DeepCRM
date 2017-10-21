		@Override
		public URI build(Object... uriVars) {
			if (ObjectUtils.isEmpty(uriVars) && !defaultUriVariables.isEmpty()) {
				return build(Collections.emptyMap());
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
