	@SuppressWarnings("")
	private void assertHtmlOutput(String output) throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		List nodes = document.getRootElement().selectNodes("/html/body/table/tr");

		Element tr1 = (Element) nodes.get(0);
		assertRowElement(tr1, "1", "Whatsit", "12.99");
		Element tr2 = (Element) nodes.get(1);
		assertRowElement(tr2, "2", "Thingy", "13.99");
		Element tr3 = (Element) nodes.get(2);
		assertRowElement(tr3, "3", "Gizmo", "14.99");
		Element tr4 = (Element) nodes.get(3);
		assertRowElement(tr4, "4", "Cranktoggle", "11.99");
	}
