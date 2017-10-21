	private void addToToBeLinkedQueue(
			MappingDocument mappingDocument,
			JaxbHbmSubclassEntityBaseDefinition jaxbSubEntityMapping) {
		List<ExtendsQueueEntry> waitingList = null;

		if ( toBeLinkedQueue == null ) {
			toBeLinkedQueue = new HashMap<String, List<ExtendsQueueEntry>>();
		}
		else {
			waitingList = toBeLinkedQueue.get( jaxbSubEntityMapping.getExtends() );
		}

		if ( waitingList == null ) {
			waitingList = new ArrayList<ExtendsQueueEntry>();
			toBeLinkedQueue.put( jaxbSubEntityMapping.getExtends(), waitingList );
		}

		waitingList.add( new ExtendsQueueEntry( mappingDocument, jaxbSubEntityMapping ) );
	}
