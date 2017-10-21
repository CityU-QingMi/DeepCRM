	private <T> void validate(T object, EntityMode mode, EntityPersister persister,
			SessionFactoryImplementor sessionFactory, GroupsPerOperation.Operation operation) {
		if ( object == null || mode != EntityMode.POJO ) {
			return;
		}
		TraversableResolver tr = new HibernateTraversableResolver(
				persister, associationsPerEntityPersister, sessionFactory
		);
		Validator validator = factory.usingContext()
				.traversableResolver( tr )
				.getValidator();
		final Class<?>[] groups = groupsPerOperation.get( operation );
		if ( groups.length > 0 ) {
			final Set<ConstraintViolation<T>> constraintViolations = validator.validate( object, groups );
			if ( constraintViolations.size() > 0 ) {
				Set<ConstraintViolation<?>> propagatedViolations =
						new HashSet<ConstraintViolation<?>>( constraintViolations.size() );
				Set<String> classNames = new HashSet<String>();
				for ( ConstraintViolation<?> violation : constraintViolations ) {
					LOG.trace( violation );
					propagatedViolations.add( violation );
					classNames.add( violation.getLeafBean().getClass().getName() );
				}
				StringBuilder builder = new StringBuilder();
				builder.append( "Validation failed for classes " );
				builder.append( classNames );
				builder.append( " during " );
				builder.append( operation.getName() );
				builder.append( " time for groups " );
				builder.append( toString( groups ) );
				builder.append( "\nList of constraint violations:[\n" );
				for (ConstraintViolation<?> violation : constraintViolations) {
					builder.append( "\t" ).append( violation.toString() ).append("\n");
				}
				builder.append( "]" );

				throw new ConstraintViolationException(
						builder.toString(), propagatedViolations
				);
			}
		}
	}
