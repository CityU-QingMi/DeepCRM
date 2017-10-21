    private DisplayGroup elementToDisplayGroup(Element element) {
        DisplayGroup displaygroup = new DisplayGroup();
        
        displaygroup.setName(element.getAttributeValue("name"));
        displaygroup.setKey(element.getAttributeValue("key"));
        
        List<Element> displaygroups = element.getChildren("property-def");
        for (Element e : displaygroups) {
            displaygroup.addPropertyDef(this.elementToPropertyDef(e));
        }
        
        return displaygroup;
    }
