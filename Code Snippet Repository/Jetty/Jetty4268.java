        @Override
        public String[] getParameterValues(String name)
        {
            List l=_params.getValues(name);
            if (l==null || l.size()==0)
                return new String[0];
            String[] v = new String[l.size()];
            for (int i=0;i<l.size();i++)
            {
                Object o=l.get(i);
                if (o instanceof byte[])
                {
                    try
                    {
                        v[i]=getParameterBytesAsString(name, (byte[])o);
                    }
                    catch(Exception e)
                    {
                        throw new RuntimeException(e);
                    }
                }
                else if (o instanceof String)
                    v[i]=(String)o;
            }
            return v;
        }
