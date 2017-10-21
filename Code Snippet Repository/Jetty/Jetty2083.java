        public void addEntries(long start, long stop)
        {
            if (start > 0 && stop > 0)
            {
                for(long idx=start; idx <= stop; idx++)
                {
                    add(idx);
                }
            }
        }
