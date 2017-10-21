        @Override
        public boolean add(Entry entry)
        {
            if (entry instanceof PackageEntry)
                return _byPackage.add(entry);

            if (entry instanceof ClassEntry)
            {
                // Add class name to packages also as classes act
                // as packages for nested classes.
                boolean added = _byPackage.add(entry);
                added = _byClass.add(entry) || added;
                return added;
            }

            throw new IllegalArgumentException();
        }
