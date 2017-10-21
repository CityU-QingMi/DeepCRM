        @Override
        public void copy(Path from, Path to) throws IOException
        {
            if(Files.exists(to)) 
            {
                // skip if it exists
                return;
            }
            Files.createFile(to);
        }
