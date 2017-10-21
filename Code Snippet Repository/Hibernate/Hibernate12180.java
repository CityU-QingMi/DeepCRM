	private static void updateEmbeddableAccessTypeForMember(Context context, AccessType defaultAccessType, Element member) {
		EmbeddedAttributeVisitor visitor = new EmbeddedAttributeVisitor( context );
		String embeddedClassName = member.asType().accept( visitor, member );
		if ( embeddedClassName != null ) {
			AccessTypeInformation accessTypeInfo = context.getAccessTypeInfo( embeddedClassName );
			if ( accessTypeInfo == null ) {
				accessTypeInfo = new AccessTypeInformation( embeddedClassName, null, defaultAccessType );
				context.addAccessTypeInformation( embeddedClassName, accessTypeInfo );
			}
			else {
				accessTypeInfo.setDefaultAccessType( defaultAccessType );
			}
		}
	}
