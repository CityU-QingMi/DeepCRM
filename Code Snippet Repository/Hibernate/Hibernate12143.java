	private boolean modelGenerationNeedsToBeDeferred(Collection<MetaEntity> entities, MetaEntity containedEntity) {
		ContainsAttributeTypeVisitor visitor = new ContainsAttributeTypeVisitor(
				containedEntity.getTypeElement(), context
		);
		for ( MetaEntity entity : entities ) {
			if ( entity.equals( containedEntity ) ) {
				continue;
			}
			for ( Element subElement : ElementFilter.fieldsIn( entity.getTypeElement().getEnclosedElements() ) ) {
				TypeMirror mirror = subElement.asType();
				if ( !TypeKind.DECLARED.equals( mirror.getKind() ) ) {
					continue;
				}
				boolean contains = mirror.accept( visitor, subElement );
				if ( contains ) {
					return true;
				}
			}
			for ( Element subElement : ElementFilter.methodsIn( entity.getTypeElement().getEnclosedElements() ) ) {
				TypeMirror mirror = subElement.asType();
				if ( !TypeKind.DECLARED.equals( mirror.getKind() ) ) {
					continue;
				}
				boolean contains = mirror.accept( visitor, subElement );
				if ( contains ) {
					return true;
				}
			}
		}
		return false;
	}
