        private String expandParameter(String value)
        {
            int i=0;
            while (true)
            {
                int open=value.indexOf("${",i);
                if (open<0)
                    return value;
                int close=value.indexOf("}",open);
                if (close<0)
                    return value;
                
                String param = value.substring(open+2,close);
                if (_params.containsKey(param))
                {
                    String tmp=value.substring(0,open)+_params.get(param);
                    i=tmp.length();
                    value=tmp+value.substring(close+1);
                }
                else
                    i=close+1;
            }
        }
