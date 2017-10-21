	private void collectPersistentAttributesUsingClassLevelAccessType(
			TreeMap<String, XProperty> persistentAttributeMap,
			Map<String,XProperty> persistentAttributesFromGetters,
			List<XProperty> fields,
			List<XProperty> getters) {
		if ( classLevelAccessType == AccessType.FIELD ) {
			for ( XProperty field : fields ) {
				if ( persistentAttributeMap.containsKey( field.getName() ) ) {
					continue;
				}

				persistentAttributeMap.put( field.getName(), field );
			}
		}
		else {
			for ( XProperty getter : getters ) {
				final String name = getter.getName();

				// HHH-10242 detect registration of the same property getter twice - eg boolean isId() + UUID getId()
				final XProperty previous = persistentAttributesFromGetters.get( name );
				if ( previous != null ) {
					throw new org.hibernate.boot.MappingException(
							LOG.ambiguousPropertyMethods(
									xClass.getName(),
									HCANNHelper.annotatedElementSignature( previous ),
									HCANNHelper.annotatedElementSignature( getter )
							),
							new Origin( SourceType.ANNOTATION, xClass.getName() )
					);
				}

				if ( persistentAttributeMap.containsKey( name ) ) {
					continue;
				}

				persistentAttributeMap.put( getter.getName(), getter );
				persistentAttributesFromGetters.put( name, getter );
			}
		}
	}
