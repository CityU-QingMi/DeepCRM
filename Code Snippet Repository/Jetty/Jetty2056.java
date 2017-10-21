    protected String getNameString()
    {
        StringBuilder result = new StringBuilder();
        
        result.append('[');
        result.append(_objectName);
        result.append(":");
        result.append(_attributeName);
        result.append("]");
        
        return result.toString();
    }
