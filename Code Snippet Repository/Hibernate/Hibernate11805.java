	@Override
	public <S> LinkedHashSet<S> loadJavaServices(Class<S> serviceContract) {
		Iterable<S> parentDiscoveredServices = super.loadJavaServices( serviceContract );
		S[] serviceImpls = osgiServiceUtil.getServiceImpls(serviceContract);
		LinkedHashSet<S> composite = new LinkedHashSet<S>();
		for ( S service : parentDiscoveredServices ) {
			composite.add( service );
		}
		for ( S service : serviceImpls ) {
			composite.add( service );
		}
		return composite;
	}
