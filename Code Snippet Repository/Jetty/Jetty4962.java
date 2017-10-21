        private void check()
        {
            if (Files.exists(path))
            {
                try
                {
                    modified = Files.getLastModifiedTime(path).toMillis();
                    length = Files.size(path);
                }
                catch(IOException e)
                {
                    modified = -1;
                    length = -1;
                }
            }
            else
            {
                modified = -1;
                length = -1;
            }            
        }
