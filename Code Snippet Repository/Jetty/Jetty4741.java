    public void toJSON(Object obj, Output out)
    {
        if(_fromJSON)
            out.addClass(_pojoClass);
        for(Map.Entry<String,Method> entry : _getters.entrySet())
        {            
            try
            {
                out.add(entry.getKey(), entry.getValue().invoke(obj, GETTER_ARG));                    
            }
            catch(Exception e)
            {
                // TODO throw exception?
                LOG.warn("{} property '{}' excluded. (errors)", _pojoClass.getName(), 
                        entry.getKey());
                log(e);
            }
        }        
    }
