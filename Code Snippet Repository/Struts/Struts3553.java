    public void testParseConfigWithBang() {
    	MockPortletContext portletContext = new MockPortletContext();
    	MockPortletConfig portletConfig = new MockPortletConfig(portletContext);

    	portletConfig.addInitParameter("viewNamespace", "/view");
    	portletConfig.addInitParameter("defaultViewAction", "index!input");
    	
    	Map<PortletMode, ActionMapping> actionMap = new HashMap<PortletMode, ActionMapping>();
    	
    	dispatcher.parseModeConfig(actionMap, portletConfig, PortletMode.VIEW, "viewNamespace", "defaultViewAction");
    	
    	ActionMapping mapping = actionMap.get(PortletMode.VIEW);
    	assertEquals("index", mapping.getName());
    	assertEquals("/view", mapping.getNamespace());
    	assertEquals("input", mapping.getMethod());
    }
