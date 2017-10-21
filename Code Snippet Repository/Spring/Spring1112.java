	@Test
	public void cornerSpr8937AndSpr12582() throws IntrospectionException {
		@SuppressWarnings("unused") class A {
			public void setAddress(String addr){ }
			public void setAddress(int index, String addr) { }
			public String getAddress(int index){ return null; }
		}

		// Baseline:
		BeanInfo bi = Introspector.getBeanInfo(A.class);
		boolean hasReadMethod = hasReadMethodForProperty(bi, "address");
		boolean hasWriteMethod = hasWriteMethodForProperty(bi, "address");
		boolean hasIndexedReadMethod = hasIndexedReadMethodForProperty(bi, "address");
		boolean hasIndexedWriteMethod = hasIndexedWriteMethodForProperty(bi, "address");

		// ExtendedBeanInfo needs to behave exactly like BeanInfo...
		BeanInfo ebi = new ExtendedBeanInfo(bi);
		assertEquals(hasReadMethod, hasReadMethodForProperty(ebi, "address"));
		assertEquals(hasWriteMethod, hasWriteMethodForProperty(ebi, "address"));
		assertEquals(hasIndexedReadMethod, hasIndexedReadMethodForProperty(ebi, "address"));
		assertEquals(hasIndexedWriteMethod, hasIndexedWriteMethodForProperty(ebi, "address"));
	}
