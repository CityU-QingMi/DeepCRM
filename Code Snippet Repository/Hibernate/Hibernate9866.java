	private PersistentClass getReferenceCollectionClass(Collection collectionValue) {
		PersistentClass referencedClass = null;
		if ( collectionValue.getElement() instanceof OneToMany ) {
			final OneToMany oneToManyValue = (OneToMany) collectionValue.getElement();
			referencedClass = oneToManyValue.getAssociatedClass();
		}
		else if ( collectionValue.getElement() instanceof ManyToOne ) {
			// Case for bi-directional relation with @JoinTable on the owning @ManyToOne side.
			final ManyToOne manyToOneValue = (ManyToOne) collectionValue.getElement();
			referencedClass = manyToOneValue.getMetadata().getEntityBinding( manyToOneValue.getReferencedEntityName() );
		}
		return referencedClass;
	}
