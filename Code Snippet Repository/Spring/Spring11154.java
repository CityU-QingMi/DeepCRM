	private boolean isResourceUnderLocation(Resource resource) throws IOException {
		if (resource.getClass() != this.location.getClass()) {
			return false;
		}

		String resourcePath;
		String locationPath;

		if (resource instanceof UrlResource) {
			resourcePath = resource.getURL().toExternalForm();
			locationPath = StringUtils.cleanPath(this.location.getURL().toString());
		}
		else if (resource instanceof ClassPathResource) {
			resourcePath = ((ClassPathResource) resource).getPath();
			locationPath = StringUtils.cleanPath(((ClassPathResource) this.location).getPath());
		}
		else {
			resourcePath = resource.getURL().getPath();
			locationPath = StringUtils.cleanPath(this.location.getURL().getPath());
		}

		if (locationPath.equals(resourcePath)) {
			return true;
		}
		locationPath = (locationPath.endsWith("/") || locationPath.isEmpty() ? locationPath :
				locationPath + "/");
		if (!resourcePath.startsWith(locationPath)) {
			return false;
		}

		if (resourcePath.contains("%")) {
			if (StringUtils.uriDecode(resourcePath, StandardCharsets.UTF_8).contains("../")) {
				return false;
			}
		}
		return true;
	}
