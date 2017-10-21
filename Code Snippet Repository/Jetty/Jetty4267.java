        @Override
        public Map<String, String[]> getParameterMap()
        {
            Map<String, String[]> cmap = new HashMap<String,String[]>();
            
            for ( Object key : _params.keySet() )
            {
                cmap.put((String)key,getParameterValues((String)key));
            }

            return Collections.unmodifiableMap(cmap);
        }
