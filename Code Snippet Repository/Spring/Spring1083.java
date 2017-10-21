	private static void performSet() {
		TestBean bean = new TestBean();

		Properties p = (Properties) System.getProperties().clone();

		assertTrue("The System properties must not be empty", p.size() != 0);

		for (Iterator<?> i = p.entrySet().iterator(); i.hasNext();) {
			i.next();
			if (Math.random() > 0.9) {
				i.remove();
			}
		}

		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			p.store(buffer, null);
		}
		catch (IOException e) {
			// ByteArrayOutputStream does not throw
			// any IOException
		}
		String value = new String(buffer.toByteArray());

		BeanWrapperImpl wrapper = new BeanWrapperImpl(bean);
		wrapper.setPropertyValue("properties", value);
		assertEquals(p, bean.getProperties());
	}
