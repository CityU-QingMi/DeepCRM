	@Test
	public void testIndexNode() throws Exception {
		IndexNode n = new IndexNode();
		Exception ex = null;
		try {
			n.setScalarColumn( 0 );
		}
		catch ( UnsupportedOperationException e ) {
			ex = e;
		}
		assertNotNull( ex );
	}
