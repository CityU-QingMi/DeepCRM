        @Override
        public boolean add(Entry entry)
        {
            if (entry instanceof LocationEntry)
                return _byLocation.add(entry);
            if (entry instanceof ModuleEntry)
                return _byModule.add(entry);

            throw new IllegalArgumentException(entry.toString());
        }
