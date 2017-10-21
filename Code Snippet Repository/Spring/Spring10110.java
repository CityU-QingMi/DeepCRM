	@Test
	public void toExpectedQName() {
		assertEquals(new QName("pojo"), this.decoder.toQName(Pojo.class));
		assertEquals(new QName("pojo"), this.decoder.toQName(TypePojo.class));

		assertEquals(new QName("namespace", "name"),
				this.decoder.toQName(XmlRootElementWithNameAndNamespace.class));
		assertEquals(new QName("namespace", "name"),
				this.decoder.toQName(XmlRootElementWithName.class));
		assertEquals(new QName("namespace", "xmlRootElement"),
				this.decoder.toQName(XmlRootElement.class));

		assertEquals(new QName("namespace", "name"),
				this.decoder.toQName(XmlTypeWithNameAndNamespace.class));
		assertEquals(new QName("namespace", "name"),
				this.decoder.toQName(XmlTypeWithName.class));
		assertEquals(new QName("namespace", "xmlType"),
				this.decoder.toQName(XmlType.class));

	}
