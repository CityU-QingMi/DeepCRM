        @Override
        public boolean test(URI uri)
        {
            if (!uri.getScheme().equalsIgnoreCase("jrt"))
                return false;
            String module = uri.getPath();
            int end = module.indexOf('/',1);
            if (end<1)
                end = module.length();
            return _entries.get(module,1,end-1)!=null;
        }
