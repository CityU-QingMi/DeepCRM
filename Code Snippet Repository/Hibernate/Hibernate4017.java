		@SuppressWarnings({""})
		protected BaseAttributeMetadata(
				Property propertyMapping,
				AbstractManagedType<X> ownerType,
				Member member,
				Attribute.PersistentAttributeType persistentAttributeType) {
			this.propertyMapping = propertyMapping;
			this.ownerType = ownerType;
			this.member = member;
			this.persistentAttributeType = persistentAttributeType;

			final Class declaredType;

			if ( member == null ) {
				// assume we have a MAP entity-mode "class"
				declaredType = propertyMapping.getType().getReturnedClass();
			}
			else if ( Field.class.isInstance( member ) ) {
				declaredType = ( (Field) member ).getType();
			}
			else if ( Method.class.isInstance( member ) ) {
				declaredType = ( (Method) member ).getReturnType();
			}
			else if ( MapMember.class.isInstance( member ) ) {
				declaredType = ( (MapMember) member ).getType();
			}
			else {
				throw new IllegalArgumentException( "Cannot determine java-type from given member [" + member + "]" );
			}
			this.javaType = accountForPrimitiveTypes( declaredType );
		}
