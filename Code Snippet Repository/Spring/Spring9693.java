		@Override
		public void sendRedirect(String location) throws IOException {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(location);

			// Absolute location
			if (builder.build().getScheme() != null) {
				super.sendRedirect(location);
				return;
			}

			// Network-path reference
			if (location.startsWith("//")) {
				String scheme = this.request.getScheme();
				super.sendRedirect(builder.scheme(scheme).toUriString());
				return;
			}

			// Relative to Servlet container root or to current request
			String path = (location.startsWith(FOLDER_SEPARATOR) ? location :
					StringUtils.applyRelativePath(this.request.getRequestURI(), location));

			String result = UriComponentsBuilder
					.fromHttpRequest(new ServletServerHttpRequest(this.request))
					.replacePath(path)
					.build().normalize().toUriString();

			super.sendRedirect(result);
		}
