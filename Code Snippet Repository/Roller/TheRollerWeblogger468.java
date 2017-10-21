    private static String getLdapAttribute(Attributes attributes, String name) {
        if(attributes == null) {
            return null;
        }
        
        Attribute attribute = attributes.get(name);
        
        if(attribute == null) {
            return null;
        }
        
        Object oValue  = null;
        try {
            oValue = attribute.get();
        } catch (NamingException e) {
            return null;
        }
        
        if (oValue == null) {
            return null;
        }
        
        return oValue.toString();
    }
