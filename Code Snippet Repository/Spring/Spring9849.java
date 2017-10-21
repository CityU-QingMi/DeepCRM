		private UriComponentsBuilder initUriComponentsBuilder(String uriTemplate) {

			UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(uriTemplate);
			UriComponents uriComponents = uriComponentsBuilder.build();

			UriComponentsBuilder result = (uriComponents.getHost() == null ?
					baseUri.cloneBuilder().uriComponents(uriComponents) : uriComponentsBuilder);

			if (shouldParsePath()) {
				UriComponents uric = result.build();
				String path = uric.getPath();
				List<String> pathSegments = uric.getPathSegments();

				result.replacePath(null);
				result.pathSegment(pathSegments.toArray(new String[0]));

				if (path != null && path.endsWith("/")) {
					result.path("/");
				}
			}

			return result;
		}
