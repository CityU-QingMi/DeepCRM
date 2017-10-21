	private void assertLoadedFromCache(MyListener listener, Integer custId, Set contactIds) {
		assertTrue(
				"Customer#" + custId + " was in cache", listener.visited.contains(
				"Customer#"
						+ custId
		)
		);
		for ( Iterator it = contactIds.iterator(); it.hasNext(); ) {
			Integer contactId = (Integer) it.next();
			assertTrue(
					"Contact#" + contactId + " was in cache", listener.visited.contains(
					"Contact#"
							+ contactId
			)
			);
			assertTrue(
					"Contact#" + contactId + " was in cache", listener.visited.contains(
					"Contact#"
							+ contactId
			)
			);
		}
		assertTrue(
				"Customer.contacts" + custId + " was in cache", listener.visited
				.contains( "Customer.contacts#" + custId )
		);
	}
