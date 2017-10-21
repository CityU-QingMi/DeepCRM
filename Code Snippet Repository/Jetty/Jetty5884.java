        protected LocationEntry(String name, boolean inclusive)
        {
            super(name, inclusive);
            if (!getName().startsWith("file:"))
                throw new IllegalArgumentException(name);
            try
            {
                _file = Resource.newResource(getName()).getFile();
            }
            catch(IOException e)
            {
                throw new RuntimeIOException(e);
            }
        }
