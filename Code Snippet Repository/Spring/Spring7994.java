	@Override
	public void afterPropertiesSet() throws CastorMappingException, IOException {
		try {
			this.xmlContext = createXMLContext(this.mappingLocations, this.targetClasses, this.targetPackages);
		}
		catch (MappingException ex) {
			throw new CastorMappingException("Could not load Castor mapping", ex);
		}
		catch (ResolverException ex) {
			throw new CastorMappingException("Could not resolve Castor mapping", ex);
		}
	}
