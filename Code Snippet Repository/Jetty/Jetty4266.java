        @Override
        public String getParameter(String name)
        {
            Object o=_params.get(name);
            if (!(o instanceof byte[]) && LazyList.size(o)>0)
                o=LazyList.get(o,0);

            if (o instanceof byte[])
            {
                try
                {
                    return getParameterBytesAsString(name, (byte[])o);
                }
                catch(Exception e)
                {
                    LOG.warn(e);
                }
            }
            else if (o!=null)
                return String.valueOf(o);
            return null;
        }
