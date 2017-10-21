		@Override
		protected List<ApplicationResource> getSources(ApplicationContext applicationContext) {
			if (definitions != null) {
				List<ApplicationResource> result = new LinkedList<>();
				for (String definition : definitions) {
					Collection<ApplicationResource> resources = applicationContext.getResources(definition);
					if (resources != null) {
						result.addAll(resources);
					}
				}
				return result;
			}
			else {
				return super.getSources(applicationContext);
			}
		}
