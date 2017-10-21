        @Override
        public synchronized Enumeration<String> getAttributeNames()
        {
            HashSet<String> set = new HashSet<String>();
            Enumeration<String> e = super.getAttributeNames();
            while (e.hasMoreElements())
                set.add(e.nextElement());
            e = _attributes.getAttributeNames();
            while (e.hasMoreElements())
                set.add(e.nextElement());

            return Collections.enumeration(set);
        }
