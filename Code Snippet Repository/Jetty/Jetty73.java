        @Override
        public int compare(ServletContainerInitializer sci1, ServletContainerInitializer sci2)
        {
            String c1 = (sci1 != null? sci1.getClass().getName() : null);
            String c2 = (sci2 != null? sci2.getClass().getName() : null);

            if (c1 == null && c2 == null)
                return 0;
            
            int i1 = _ordering.getIndexOf(c1);
            if (i1 < 0 && _ordering.hasWildcard())
                i1 = _ordering.getWildcardIndex();
            int i2 = _ordering.getIndexOf(c2);
            if (i2 < 0 && _ordering.hasWildcard())
                i2 = _ordering.getWildcardIndex();
           
            return Integer.compare(i1, i2);
        }
