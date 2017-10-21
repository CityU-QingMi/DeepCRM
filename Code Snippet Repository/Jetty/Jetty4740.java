    public int setProps(Object obj, Map<?,?> props)
    {
        int count = 0;
        for(Iterator<?> iterator = props.entrySet().iterator(); iterator.hasNext();)
        {
            Map.Entry<?, ?> entry = (Map.Entry<?,?>) iterator.next();
            Setter setter = getSetter((String)entry.getKey());
            if(setter!=null)
            {
                try
                {
                    setter.invoke(obj, entry.getValue());                    
                    count++;
                }
                catch(Exception e)
                {
                    // TODO throw exception?
                    LOG.warn(_pojoClass.getName()+"#"+setter.getPropertyName()+" not set from "+
                            (entry.getValue().getClass().getName())+"="+entry.getValue().toString());
                    log(e);
                }
            }
        }
        return count;
    }
