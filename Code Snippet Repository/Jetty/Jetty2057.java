    protected TYPE getValue(CompositeData compValue, String fieldName)
    {
        int pos = fieldName.indexOf('.');
        if (pos < 0)
            return (TYPE)compValue.get(fieldName);
        else
            return getValue((CompositeData)compValue.get(fieldName.substring(0, pos)), 
                            fieldName.substring(pos+1));
          
    }
