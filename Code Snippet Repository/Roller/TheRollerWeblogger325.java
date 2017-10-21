    private PropertyDef elementToPropertyDef(Element element) {
        PropertyDef prop = new PropertyDef();
        
        prop.setName(element.getAttributeValue("name"));
        prop.setKey(element.getAttributeValue("key"));
        prop.setType(element.getChildText("type"));
        prop.setDefaultValue(element.getChildText("default-value"));
        
        // optional elements
        if (element.getChild("rows") != null) {
            prop.setRows(element.getChildText("rows"));
        }
        
        if (element.getChild("cols") != null) {
            prop.setCols(element.getChildText("cols"));
        }
        
        return prop;
    }
