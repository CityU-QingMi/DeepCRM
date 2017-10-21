    @ManagedAttribute("")
    public Map<String,Object> getContextAttributes()
    {
        Map<String,Object> map = new HashMap<String,Object>();
        Attributes attrs = ((ContextHandler)_managed).getAttributes();
        Enumeration<String> en = attrs.getAttributeNames();
        while (en.hasMoreElements())
        {
            String name = (String)en.nextElement();
            Object value = attrs.getAttribute(name);
            map.put(name,value);
        }
        return map;
    }
