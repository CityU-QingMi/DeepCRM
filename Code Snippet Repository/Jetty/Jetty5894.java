        @Override
        public boolean add(Entry entry)
        {
            if (!(entry instanceof ModuleEntry))
                throw new IllegalArgumentException(entry.toString());
            String module = ((ModuleEntry)entry).getModule();

            if (_entries.get(module)!=null)
                return false;
            _entries.put(module,entry);
            return true;
        }
