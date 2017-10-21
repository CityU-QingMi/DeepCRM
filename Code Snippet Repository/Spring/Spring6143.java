	@Override
	protected Resource[] createInstance() throws Exception {
		List<Resource> scripts = new ArrayList<>();
		for (String location : this.locations) {
			List<Resource> resources = new ArrayList<>(
					Arrays.asList(this.resourcePatternResolver.getResources(location)));
			Collections.sort(resources, (r1, r2) -> {
				try {
					return r1.getURL().toString().compareTo(r2.getURL().toString());
				}
				catch (IOException ex) {
					return 0;
				}
			});
			for (Resource resource : resources) {
				scripts.add(resource);
			}
		}
		return scripts.toArray(new Resource[scripts.size()]);
	}
