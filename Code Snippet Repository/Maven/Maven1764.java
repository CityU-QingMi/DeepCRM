    public Map<String, Parameter> getParameterMap()
    {
        if ( parameterMap == null )
        {
            parameterMap = new HashMap<>();

            if ( parameters != null )
            {
                for ( Parameter pd : parameters )
                {
                    parameterMap.put( pd.getName(), pd );
                }
            }
        }

        return parameterMap;
    }
