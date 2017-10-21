				@Override
				public Map<String, EventType> run() {
					final Map<String, EventType> typeByNameMap = new HashMap<String, EventType>();
					for ( Field field : EventType.class.getDeclaredFields() ) {
						if ( EventType.class.isAssignableFrom( field.getType() ) ) {
							try {
								final EventType typeField = (EventType) field.get( null );
								typeByNameMap.put( typeField.eventName(), typeField );
							}
							catch (Exception t) {
								throw new HibernateException( "Unable to initialize EventType map", t );
							}
						}
					}
					return typeByNameMap;
				}
