	public EntityDeleteAction(
			final Serializable id,
			final Object[] state,
			final Object version,
			final Object instance,
			final EntityPersister persister,
			final boolean isCascadeDeleteEnabled,
			final SessionImplementor session) {
		super( session, id, instance, persister );
		this.version = version;
		this.isCascadeDeleteEnabled = isCascadeDeleteEnabled;
		this.state = state;

		// before remove we need to remove the local (transactional) natural id cross-reference
		naturalIdValues = session.getPersistenceContext().getNaturalIdHelper().removeLocalNaturalIdCrossReference(
				getPersister(),
				getId(),
				state
		);
	}
