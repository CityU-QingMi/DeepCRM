        @Override
        public boolean add(Entry entry)
        {
            String name = entry.getName();
            if (entry instanceof ClassEntry)
                name+="$";
            else if (!(entry instanceof PackageEntry))
                throw new IllegalArgumentException(entry.toString());
            else if (".".equals(name))
                name="";
                
            if (_entries.get(name)!=null)
                return false;
            
            return _entries.put(name,entry);
        }
