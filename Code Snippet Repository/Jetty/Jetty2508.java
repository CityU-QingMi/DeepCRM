    private String getString(String property)
    {
        if(property==null)
        {
            return null;
        }

        Attribute a = attributes.get(property);
        if (a!=null)
            return a.value;

        // Use system properties next
        return System.getProperty(property);
    }
