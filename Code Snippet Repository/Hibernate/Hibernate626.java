	public static MappingReference consume(JaxbCfgMappingReferenceType jaxbMapping) {
		if ( StringHelper.isNotEmpty( jaxbMapping.getClazz() ) ) {
			return new MappingReference( MappingReference.Type.CLASS, jaxbMapping.getClazz() );
		}
		else if ( StringHelper.isNotEmpty( jaxbMapping.getFile() ) ) {
			return  new MappingReference( MappingReference.Type.FILE, jaxbMapping.getFile() );
		}
		else if ( StringHelper.isNotEmpty( jaxbMapping.getResource() ) ) {
			return new MappingReference( MappingReference.Type.RESOURCE, jaxbMapping.getResource() );
		}
		else if ( StringHelper.isNotEmpty( jaxbMapping.getJar() ) ) {
			return new MappingReference( MappingReference.Type.JAR, jaxbMapping.getJar() );
		}
		else if ( StringHelper.isNotEmpty( jaxbMapping.getPackage() ) ) {
			return new MappingReference( MappingReference.Type.PACKAGE, jaxbMapping.getPackage() );
		}
		else {
			throw new ConfigurationException( "<mapping/> named unexpected reference type" );
		}
	}
