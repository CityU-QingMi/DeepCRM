	@Override
	public Object mapToObjectFromFullMap(
			EntityInstantiator entityInstantiator,
			Map<String, Object> data,
			Object dataObject,
			Number revision) {
		try {
			final Object componentInstance = dataObject != null
					? dataObject
					: ReflectHelper.getDefaultConstructor( componentClass ).newInstance();
			delegate.mapToEntityFromMap(
					entityInstantiator.getEnversService(),
					componentInstance,
					data,
					null,
					entityInstantiator.getAuditReaderImplementor(),
					revision
			);
			return componentInstance;
		}
		catch (Exception e) {
			throw new AuditException( e );
		}
	}
