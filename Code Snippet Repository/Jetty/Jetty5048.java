        @Override
        public int compare(T o1, T o2)
        {
            Integer i1=_indexes.get(o1);
            Integer i2=_indexes.get(o2);
            if (i1==null || i2==null || i1.equals(o2))
                return 0;
            if (i1<i2)
                return -1;
            return 1;
        }
