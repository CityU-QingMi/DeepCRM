        @Override
        protected void parsedParam(StringBuffer buffer, int valueLength, int paramName, int paramValue)
        {
            if (valueLength==0 && paramValue>paramName)
            {
                String name=StringUtil.asciiToLowerCase(buffer.substring(paramName,paramValue-1));
                if ("for".equalsIgnoreCase(name))
                {
                    String value=buffer.substring(paramValue);
                    
                    // if unknown, clear any leftward values
                    if ("unknown".equalsIgnoreCase(value))
                        _for = null;
                    // Otherwise accept IP or token(starting with '_') as remote keys
                    else
                        _for=value;
                }
            }
        }
