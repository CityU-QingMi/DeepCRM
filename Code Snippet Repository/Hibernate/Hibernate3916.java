	public Class getProxyInterface() {
		if ( proxyInterfaceName == null ) {
			return null;
		}
		try {
			if ( proxyInterface == null ) {
				proxyInterface = metadataBuildingContext.getClassLoaderAccess().classForName( proxyInterfaceName );
			}
			return proxyInterface;
		}
		catch (ClassLoadingException e) {
			throw new MappingException( "proxy class not found: " + proxyInterfaceName, e );
		}
	}
