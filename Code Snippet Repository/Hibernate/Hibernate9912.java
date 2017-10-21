		public ComponentPropertiesSource(ReflectionManager reflectionManager, Component component) {
			try {
				this.xclass = reflectionManager.classForName( component.getComponentClassName() );
			}
			catch ( ClassLoadingException e ) {
				throw new MappingException( e );
			}

			this.component = component;
		}
