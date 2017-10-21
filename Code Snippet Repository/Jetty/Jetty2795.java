        @Override
        public Enumeration<String> getAttributeNames()
        {
            HashSet<String> set=new HashSet<>();
            Enumeration<String> e=_attr.getAttributeNames();
            while(e.hasMoreElements())
            {
                String name=e.nextElement();
                if (!name.startsWith(__INCLUDE_PREFIX))
                    set.add(name);
            }

            if (_named==null)
            {
                if (_pathInfo!=null)
                    set.add(INCLUDE_PATH_INFO);
                else
                    set.remove(INCLUDE_PATH_INFO);
                set.add(INCLUDE_REQUEST_URI);
                set.add(INCLUDE_SERVLET_PATH);
                set.add(INCLUDE_CONTEXT_PATH);
                if (_query!=null)
                    set.add(INCLUDE_QUERY_STRING);
                else
                    set.remove(INCLUDE_QUERY_STRING);
            }

            return Collections.enumeration(set);
        }
