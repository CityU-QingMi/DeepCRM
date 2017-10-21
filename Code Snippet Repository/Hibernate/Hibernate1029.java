	private static String getMappedByManyToMany(FieldDescription target, TypeDescription targetEntity, ByteBuddyEnhancementContext context) {
		for ( FieldDescription f : targetEntity.getDeclaredFields() ) {
			if ( context.isPersistentField( f )
					&& target.getName().equals( getMappedByNotManyToMany( f ) )
					&& target.getDeclaringType().asErasure().isAssignableTo( entityType( f.getType() ) ) ) {
				log.debugf(
						"mappedBy association for field [%s#%s] is [%s#%s]",
						target.getDeclaringType().asErasure().getName(),
						target.getName(),
						targetEntity.getName(),
						f.getName()
				);
				return f.getName();
			}
		}
		return null;
	}
