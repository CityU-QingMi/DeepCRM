        public boolean isHidden(Path path)
        {
            try
            {
                if (!path.startsWith(this.path))
                    return true;
                for (int i=this.path.getNameCount(); i<path.getNameCount();i++)
                {
                    if (path.getName(i).toString().startsWith("."))
                    {
                        return true;
                    }
                }
                return Files.exists(path) && Files.isHidden(path);
            }
            catch (IOException e)
            {
                LOG.ignore(e);
                return false;
            }
        }
