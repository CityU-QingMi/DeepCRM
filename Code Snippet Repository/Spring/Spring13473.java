	@Test
	public void collectionOfItemPets() throws Exception {
		this.tag.setPath("someSet");
		List allPets = new ArrayList();
		allPets.add(new ItemPet("PET1"));
		allPets.add(new ItemPet("PET2"));
		allPets.add(new ItemPet("PET3"));
		this.tag.setItems(allPets);
		this.tag.setItemValue("name");
		this.tag.setItemLabel("label");

		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = getOutput();

		// wrap the output so it is valid XML
		output = "<doc>" + output + "</doc>";

		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element spanElement1 = (Element) document.getRootElement().elements().get(0);
		Element checkboxElement1 = (Element) spanElement1.elements().get(0);
		assertEquals("input", checkboxElement1.getName());
		assertEquals("checkbox", checkboxElement1.attribute("type").getValue());
		assertEquals("someSet", checkboxElement1.attribute("name").getValue());
		assertNotNull("should be checked", checkboxElement1.attribute("checked"));
		assertEquals("checked", checkboxElement1.attribute("checked").getValue());
		assertEquals("PET1", checkboxElement1.attribute("value").getValue());
		assertEquals("PET1", spanElement1.getStringValue());
		Element spanElement2 = (Element) document.getRootElement().elements().get(1);
		Element checkboxElement2 = (Element) spanElement2.elements().get(0);
		assertEquals("input", checkboxElement2.getName());
		assertEquals("checkbox", checkboxElement2.attribute("type").getValue());
		assertEquals("someSet", checkboxElement2.attribute("name").getValue());
		assertNotNull("should be checked", checkboxElement2.attribute("checked"));
		assertEquals("checked", checkboxElement2.attribute("checked").getValue());
		assertEquals("PET2", checkboxElement2.attribute("value").getValue());
		assertEquals("PET2", spanElement2.getStringValue());
		Element spanElement3 = (Element) document.getRootElement().elements().get(2);
		Element checkboxElement3 = (Element) spanElement3.elements().get(0);
		assertEquals("input", checkboxElement3.getName());
		assertEquals("checkbox", checkboxElement3.attribute("type").getValue());
		assertEquals("someSet", checkboxElement3.attribute("name").getValue());
		assertNull("not checked", checkboxElement3.attribute("checked"));
		assertEquals("PET3", checkboxElement3.attribute("value").getValue());
		assertEquals("PET3", spanElement3.getStringValue());
	}
