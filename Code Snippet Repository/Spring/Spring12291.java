		@Override
		public String encodeURL(String url) {
			ResourceUrlProvider resourceUrlProvider = getResourceUrlProvider();
			if (resourceUrlProvider == null) {
				logger.debug("Request attribute exposing ResourceUrlProvider not found");
				return super.encodeURL(url);
			}

			int index = initLookupPath(resourceUrlProvider);
			if (url.startsWith(this.prefixLookupPath)) {
				int suffixIndex = getQueryParamsIndex(url);
				String suffix = url.substring(suffixIndex);
				String lookupPath = url.substring(index, suffixIndex);
				lookupPath = resourceUrlProvider.getForLookupPath(lookupPath);
				if (lookupPath != null) {
					return super.encodeURL(this.prefixLookupPath + lookupPath + suffix);
				}
			}

			return super.encodeURL(url);
		}
