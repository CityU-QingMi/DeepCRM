	public EntityBinder(
			Entity ejb3Ann,
			org.hibernate.annotations.Entity hibAnn,
			XClass annotatedClass,
			PersistentClass persistentClass,
			MetadataBuildingContext context) {
		this.context = context;
		this.persistentClass = persistentClass;
		this.annotatedClass = annotatedClass;
		bindEjb3Annotation( ejb3Ann );
		bindHibernateAnnotation( hibAnn );
	}
