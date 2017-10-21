	@SuppressWarnings({ "" })
	public SingularAttributeJoin(
			CriteriaBuilderImpl criteriaBuilder,
			Class<X> javaType,
			PathSource<O> pathSource,
			SingularAttribute<? super O, ?> joinAttribute,
			JoinType joinType) {
		super( criteriaBuilder, javaType, pathSource, joinAttribute, joinType );
		if ( Attribute.PersistentAttributeType.EMBEDDED == joinAttribute.getPersistentAttributeType() ) {
			this.model = (Bindable<X>) joinAttribute;
		}
		else {
			if ( javaType != null ) {
				this.model = (Bindable<X>) criteriaBuilder.getEntityManagerFactory().getMetamodel().managedType( javaType );
			}
			else {
				this.model = (Bindable<X>) joinAttribute.getType();
			}
		}
	}
