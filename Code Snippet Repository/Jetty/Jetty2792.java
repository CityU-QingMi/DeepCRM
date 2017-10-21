        @Override
        public Enumeration<String> getAttributeNames()
        {
            HashSet<String> set=new HashSet<>();
            Enumeration<String> e=_attr.getAttributeNames();
            while(e.hasMoreElements())
            {
                String name=e.nextElement();
                if (!name.startsWith(__INCLUDE_PREFIX) &&
                    !name.startsWith(__FORWARD_PREFIX))
                    set.add(name);
            }

            if (_named==null)
            {
                if (_pathInfo!=null)
                    set.add(FORWARD_PATH_INFO);
                else
                    set.remove(FORWARD_PATH_INFO);
                set.add(FORWARD_REQUEST_URI);
                set.add(FORWARD_SERVLET_PATH);
                set.add(FORWARD_CONTEXT_PATH);
                if (_query!=null)
                    set.add(FORWARD_QUERY_STRING);
                else
                    set.remove(FORWARD_QUERY_STRING);
            }

            return Collections.enumeration(set);
        }
