	public static void checkIfJoinColumn(Object columns, PropertyHolder holder, PropertyData property) {
		if ( !( columns instanceof Ejb3JoinColumn[] ) ) {
			throw new AnnotationException(
					"@Column cannot be used on an association property: "
							+ holder.getEntityName()
							+ "."
							+ property.getPropertyName()
			);
		}
	}
