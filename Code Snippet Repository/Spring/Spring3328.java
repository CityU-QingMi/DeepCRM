		@Override
		public org.springframework.core.env.PropertySource createPropertySource(String name, EncodedResource resource) throws IOException {
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			return new org.springframework.core.env.PropertySource<Properties>("my" + name, props) {
				@Override
				public Object getProperty(String name) {
					String value = props.getProperty(name);
					return (value != null ? value.toUpperCase() : null);
				}
			};
		}
