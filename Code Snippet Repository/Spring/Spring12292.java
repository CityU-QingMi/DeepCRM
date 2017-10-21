		private int initLookupPath(ResourceUrlProvider urlProvider) {
			if (this.indexLookupPath == null) {
				UrlPathHelper pathHelper = urlProvider.getUrlPathHelper();
				String requestUri = pathHelper.getRequestUri(this.request);
				String lookupPath = pathHelper.getLookupPathForRequest(this.request);
				this.indexLookupPath = requestUri.lastIndexOf(lookupPath);
				this.prefixLookupPath = requestUri.substring(0, this.indexLookupPath);
				if ("/".equals(lookupPath) && !"/".equals(requestUri)) {
					String contextPath = pathHelper.getContextPath(this.request);
					if (requestUri.equals(contextPath)) {
						this.indexLookupPath = requestUri.length();
						this.prefixLookupPath = requestUri;
					}
				}
			}
			return this.indexLookupPath;
		}
