    private ConfigDef elementToConfigDef(Element element) {
        
        ConfigDef configdef = new ConfigDef();
        
        configdef.setName(element.getAttributeValue("name"));
        
        List<Element> displaygroups = element.getChildren("display-group");
        for (Element e : displaygroups) {
            configdef.addDisplayGroup(this.elementToDisplayGroup(e));
        }
        
        return configdef;
    }
