	@Test
	public void setBothTargetClassesAndMapping() throws IOException {
		CastorMarshaller unmarshaller = new CastorMarshaller();
		unmarshaller.setMappingLocation(new ClassPathResource("order-mapping.xml", CastorMarshaller.class));
		unmarshaller.setTargetClasses(new Class[] {Order.class});
		unmarshaller.afterPropertiesSet();

		String xml = "<order>" +
				"<order-item id=\"1\" quantity=\"15\"/>" +
				"<order-item id=\"3\" quantity=\"20\"/>" +
				"</order>";

		Order order = (Order) unmarshaller.unmarshal(new StreamSource(new StringReader(xml)));
		assertEquals("Invalid amount of items", 2, order.getOrderItemCount());
		OrderItem item = order.getOrderItem(0);
		assertEquals("Invalid items", "1", item.getId());
		assertThat("Invalid items", item.getQuantity(), equalTo(15));
		item = order.getOrderItem(1);
		assertEquals("Invalid items", "3", item.getId());
		assertThat("Invalid items", item.getQuantity(), equalTo(20));
	}
