    public static boolean checkParams (Class<?>[] formalParams, Class<?>[] actualParams, boolean strict)
    {
        if (formalParams==null)
            return actualParams==null;
        if (actualParams==null)
            return false;

        if (formalParams.length!=actualParams.length)
            return false;

        if (formalParams.length==0)
            return true; 
        
        int j=0;
        if (strict)
        {
            while (j<formalParams.length && formalParams[j].equals(actualParams[j]))
                j++;
        }
        else
        { 
            while ((j<formalParams.length) && (formalParams[j].isAssignableFrom(actualParams[j])))
            {
                j++;
            }
        }

        if (j!=formalParams.length)
        {
            return false;
        }

        return true;
    }
