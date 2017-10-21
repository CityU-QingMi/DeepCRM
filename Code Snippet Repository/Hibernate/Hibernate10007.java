	@Override
	public void mapToEntityFromMap(
			EnversService enversService,
			Object obj,
			Map data,
			Object primaryKey,
			AuditReaderImplementor versionsReader,
			Number revision) {
		final Setter setter = ReflectionTools.getSetter(
				obj.getClass(),
				commonCollectionMapperData.getCollectionReferencingPropertyData(),
				enversService.getServiceRegistry()
		);
		try {
			setter.set(
					obj,
					proxyConstructor.newInstance(
							getInitializor(
									enversService,
									versionsReader,
									primaryKey,
									revision,
									RevisionType.DEL.equals(
											data.get(
													enversService.getAuditEntitiesConfiguration().getRevisionTypePropName()
											)
									)
							)
					),
					null
			);
		}
		catch (InstantiationException e) {
			throw new AuditException( e );
		}
		catch (IllegalAccessException e) {
			throw new AuditException( e );
		}
		catch (InvocationTargetException e) {
			throw new AuditException( e );
		}
	}
