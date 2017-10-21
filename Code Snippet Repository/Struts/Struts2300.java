	public void testMapWithBooleanAsKey() throws Exception {
		TestAction testAction = (TestAction) action;
		
		HashMap map = new LinkedHashMap();
		map.put(Boolean.TRUE, "male");
		map.put(Boolean.FALSE, "female");
		testAction.setMap(map);
		
		RadioTag tag = new RadioTag();
		tag.setPageContext(pageContext);
		tag.setLabel("mylabel");
		tag.setName("myname");
		tag.setValue("%{true}");
		tag.setList("map");
		
		tag.doStartTag();
		tag.doEndTag();
		
		verify(RadioTag.class.getResource("Radio-3.txt"));
	}
