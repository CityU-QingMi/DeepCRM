	public static LazyAttributesMetadata from(PersistentClass mappedEntity) {
		final Map<String, LazyAttributeDescriptor> lazyAttributeDescriptorMap = new LinkedHashMap<>();
		final Map<String, Set<String>> fetchGroupToAttributesMap = new HashMap<>();

		int i = -1;
		int x = 0;
		final Iterator itr = mappedEntity.getPropertyClosureIterator();
		while ( itr.hasNext() ) {
			i++;
			final Property property = (Property) itr.next();
			if ( property.isLazy() ) {
				final LazyAttributeDescriptor lazyAttributeDescriptor = LazyAttributeDescriptor.from( property, i, x++ );
				lazyAttributeDescriptorMap.put( lazyAttributeDescriptor.getName(), lazyAttributeDescriptor );

				final Set<String> attributeSet = fetchGroupToAttributesMap.computeIfAbsent(
						lazyAttributeDescriptor.getFetchGroupName(),
						k -> new LinkedHashSet<>()
				);
				attributeSet.add( lazyAttributeDescriptor.getName() );
			}
		}

		if ( lazyAttributeDescriptorMap.isEmpty() ) {
			return new LazyAttributesMetadata( mappedEntity.getEntityName() );
		}

		for ( Map.Entry<String, Set<String>> entry : fetchGroupToAttributesMap.entrySet() ) {
			entry.setValue( Collections.unmodifiableSet( entry.getValue() ) );
		}

		return new LazyAttributesMetadata(
				mappedEntity.getEntityName(),
				Collections.unmodifiableMap( lazyAttributeDescriptorMap ),
				Collections.unmodifiableMap( fetchGroupToAttributesMap )
		);
	}
