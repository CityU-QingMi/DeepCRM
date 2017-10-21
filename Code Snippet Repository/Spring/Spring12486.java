	@Override
	public Collection<ApplicationResource> getResources(String path) {
		Resource[] resources;
		try {
			resources = this.resolver.getResources(path);
		}
		catch (IOException ex) {
			((ServletContext) getContext()).log("Resource retrieval failed for path: " + path, ex);
			return Collections.emptyList();
		}
		if (ObjectUtils.isEmpty(resources)) {
			((ServletContext) getContext()).log("No resources found for path pattern: " + path);
			return Collections.emptyList();
		}

		Collection<ApplicationResource> resourceList = new ArrayList<>(resources.length);
		for (Resource resource : resources) {
			try {
				URL url = resource.getURL();
				resourceList.add(new URLApplicationResource(url.toExternalForm(), url));
			}
			catch (IOException ex) {
				// Shouldn't happen with the kind of resources we're using
				throw new IllegalArgumentException("No URL for " + resource, ex);
			}
		}
		return resourceList;
	}
