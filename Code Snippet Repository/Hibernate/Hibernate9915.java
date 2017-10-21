	private XProperty getProperty(XClass clazz, String propertyName) {
		final XProperty property = ReflectionTools.getProperty( clazz, propertyName );
		if ( property == null ) {
			throw new MappingException(
					"Property '" + propertyName + "' not found in class " + clazz.getName() + ". " +
							"Please revise Envers annotations applied to class " + persistentPropertiesSource.getXClass() + "."
			);
		}
		return property;
	}
