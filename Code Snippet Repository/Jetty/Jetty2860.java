    public boolean consumeAll()
    {
        synchronized (_inputQ)
        {
            try
            {
                while (true)
                {
                    Content item = nextContent();
                    if (item == null)
                        break; // Let's not bother blocking

                    skip(item, item.remaining());
                }
                return isFinished() && !isError();
            }
            catch (IOException e)
            {
                LOG.debug(e);
                return false;
            }
        }
    }
