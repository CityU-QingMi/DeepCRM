    protected String toVariableName( String methodName )
    {
        String variableName = methodName;

        if ( methodName.startsWith("get") || methodName.startsWith("set") )
        {
            variableName = variableName.substring(3);
        }
        else if ( methodName.startsWith("is") )
        {
            variableName = variableName.substring(2);
        }

        variableName = variableName.substring(0,1).toLowerCase(Locale.ENGLISH) + variableName.substring(1);

        return variableName;
    }
