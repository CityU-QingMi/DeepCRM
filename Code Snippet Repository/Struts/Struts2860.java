    public String getTextAttribute(String name) {

        String attr = getAttributeValue(name);
        if (attr != null) {
            return attr;
        }

        NamedAttribute namedAttribute = getNamedAttributeNode(name);
        if (namedAttribute == null) {
            return null;
        }

        return namedAttribute.getText();
    }
