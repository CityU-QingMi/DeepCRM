        @Override
        public boolean test(URI uri)
        {
            if (!uri.getScheme().equals("file"))
                return false;
            Path path = Paths.get(uri);

            for (Entry entry : this)
            {
                if (!(entry instanceof LocationEntry))
                    throw new IllegalStateException();

                File file = ((LocationEntry)entry).getFile();

                if (file.isDirectory())
                {
                    if (path.startsWith(file.toPath()))
                    {
                        return true;
                    }
                } else
                {
                    if (path.equals(file.toPath()))
                    {
                        return true;
                    }
                }
            }
            return false;
        }
