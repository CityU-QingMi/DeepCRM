	@Test
	public void testClassNullValue() throws Exception {
		Object key = new Object();
		assertNull(this.ccs.nullValue(key));
		int nr = this.ccs.nullInvocations().intValue();
		assertNull(this.ccs.nullValue(key));
		assertEquals(nr, this.ccs.nullInvocations().intValue());
		assertNull(this.ccs.nullValue(new Object()));
		// the check method is also cached
		assertEquals(nr, this.ccs.nullInvocations().intValue());
		assertEquals(nr + 1, AnnotatedClassCacheableService.nullInvocations.intValue());
	}
