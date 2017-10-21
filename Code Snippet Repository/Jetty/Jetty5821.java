        public Data addAllAddPathCases(String subpath, boolean exists, boolean dir) throws Exception
        {
            Data bdata = null;
            
            for (Data bcase : baseCases)
            {
                bdata = new Data(bcase, subpath, exists, dir);
                addCase(bdata);
            }
            
            return bdata;
        }
