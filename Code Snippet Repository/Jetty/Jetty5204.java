    @Override
    public boolean renameTo(Resource dest) throws SecurityException
    {
        if (dest instanceof PathResource)
        {
            PathResource destRes = (PathResource)dest;
            try
            {
                Path result = Files.move(path,destRes.path);
                return Files.exists(result,NO_FOLLOW_LINKS);
            }
            catch (IOException e)
            {
                LOG.ignore(e);
                return false;
            }
        }
        else
        {
            return false;
        }
    }
