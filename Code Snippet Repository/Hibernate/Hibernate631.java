	private ResolvedMember resolveMember(XProperty xProperty, MetadataBuildingContext buildingContext) {
		final ClassmateContext classmateContext = buildingContext.getMetadataCollector().getClassmateContext();
		final ReflectionManager reflectionManager = buildingContext.getBuildingOptions().getReflectionManager();

		final ResolvedType declaringClassType = classmateContext.getTypeResolver().resolve(
				reflectionManager.toClass( xProperty.getDeclaringClass() )
		);
		final ResolvedTypeWithMembers declaringClassWithMembers = classmateContext.getMemberResolver().resolve(
				declaringClassType,
				null,
				null
		);

		final Member member = toMember( xProperty );
		if ( member instanceof Method ) {
			for ( ResolvedMethod resolvedMember : declaringClassWithMembers.getMemberMethods() ) {
				if ( resolvedMember.getName().equals( member.getName() ) ) {
					return resolvedMember;
				}
			}
		}
		else if ( member instanceof Field ) {
			for ( ResolvedField resolvedMember : declaringClassWithMembers.getMemberFields() ) {
				if ( resolvedMember.getName().equals( member.getName() ) ) {
					return resolvedMember;
				}
			}
		}
		else {
			throw new HibernateException( "Unexpected java.lang.reflect.Member type from org.hibernate.annotations.common.reflection.java.JavaXMember : " + member );
		}

		throw new HibernateException(
				"Could not locate resolved type information for attribute [" + member.getName() + "] from Classmate"
		);
	}
