	private static String getMappedByFromTargetEntity(
			CtField persistentField,
			CtClass targetEntity,
			JavassistEnhancementContext context) throws NotFoundException {
		// get mappedBy value by searching in the fields of the target entity class
		for ( CtField f : targetEntity.getDeclaredFields() ) {
			if ( context.isPersistentField( f )
					&& getMappedByFromAnnotation( f ).equals( persistentField.getName() )
					&& isAssignable( persistentField.getDeclaringClass(), inferFieldTypeName( f ) ) ) {
				log.debugf(
						"mappedBy association for field [%s#%s] is [%s#%s]",
						persistentField.getDeclaringClass().getName(),
						persistentField.getName(),
						targetEntity.getName(),
						f.getName()
				);
				return f.getName();
			}
		}
		return "";
	}
