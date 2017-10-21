	@SuppressWarnings("")
	private void bindHibernateAnnotation(org.hibernate.annotations.Entity hibAnn) {
		{
			final DynamicInsert dynamicInsertAnn = annotatedClass.getAnnotation( DynamicInsert.class );
			this.dynamicInsert = dynamicInsertAnn == null
					? ( hibAnn == null ? false : hibAnn.dynamicInsert() )
					: dynamicInsertAnn.value();
		}

		{
			final DynamicUpdate dynamicUpdateAnn = annotatedClass.getAnnotation( DynamicUpdate.class );
			this.dynamicUpdate = dynamicUpdateAnn == null
					? ( hibAnn == null ? false : hibAnn.dynamicUpdate() )
					: dynamicUpdateAnn.value();
		}

		{
			final SelectBeforeUpdate selectBeforeUpdateAnn = annotatedClass.getAnnotation( SelectBeforeUpdate.class );
			this.selectBeforeUpdate = selectBeforeUpdateAnn == null
					? ( hibAnn == null ? false : hibAnn.selectBeforeUpdate() )
					: selectBeforeUpdateAnn.value();
		}

		{
			final OptimisticLocking optimisticLockingAnn = annotatedClass.getAnnotation( OptimisticLocking.class );
			this.optimisticLockType = optimisticLockingAnn == null
					? ( hibAnn == null ? OptimisticLockType.VERSION : hibAnn.optimisticLock() )
					: optimisticLockingAnn.type();
		}

		{
			final Polymorphism polymorphismAnn = annotatedClass.getAnnotation( Polymorphism.class );
			this.polymorphismType = polymorphismAnn == null
					? ( hibAnn == null ? PolymorphismType.IMPLICIT : hibAnn.polymorphism() )
					: polymorphismAnn.type();
		}

		if ( hibAnn != null ) {
			// used later in bind for logging
			explicitHibernateEntityAnnotation = true;
			//persister handled in bind
		}
	}
