        @Override
        protected void parsedParam(StringBuffer buffer, int valueLength, int paramName, int paramValue)
        {
            if (valueLength==0 && paramValue>paramName)
            {
                String name=StringUtil.asciiToLowerCase(buffer.substring(paramName,paramValue-1));
                String value=buffer.substring(paramValue);
                switch(name)
                {
                    case "by":
                        if (_by==null && !value.startsWith("_") && !"unknown".equals(value))
                            _by=new HostPortHttpField(value);
                        break;
                    case "for":
                        if (_for==null && !value.startsWith("_") && !"unknown".equals(value))
                            _for=new HostPortHttpField(value);
                        break;
                    case "host":
                        if (_host==null)
                            _host=new HostPortHttpField(value);
                        break;
                    case "proto":
                        if (_proto==null)
                            _proto=value;
                        break;
                }
            }
        }
