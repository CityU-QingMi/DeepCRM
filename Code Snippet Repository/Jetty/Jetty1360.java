    @Override
    protected void parsedParam(StringBuffer buffer, int valueLength, int paramName, int paramValue)
    {
        if (paramName<0)
        {
            if (buffer.charAt(buffer.length()-1)==';')
                buffer.setLength(buffer.length()-1);
        }
        else if (paramValue>=0 && 
            buffer.charAt(paramName)=='q' && paramValue>paramName && 
            buffer.length()>=paramName && buffer.charAt(paramName+1)=='=')
        {
            Double q;
            try
            {
                q=(_keepQuotes && buffer.charAt(paramValue)=='"')
                    ?new Double(buffer.substring(paramValue+1,buffer.length()-1))
                    :new Double(buffer.substring(paramValue));
            }
            catch(Exception e)
            {
                q=ZERO;
            }            
            buffer.setLength(Math.max(0,paramName-1));
            
           if (!ONE.equals(q))
               _quality.set(_quality.size()-1,q);
        }
    }
