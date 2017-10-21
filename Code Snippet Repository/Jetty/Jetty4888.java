    protected InetPattern newInetRange(String pattern)
    {
        if (pattern==null)
            return null;

        int slash = pattern.lastIndexOf('/');
        int dash = pattern.lastIndexOf('-');
        try
        {
            if (slash>=0)
                return new CidrInetRange(pattern,InetAddress.getByName(pattern.substring(0,slash).trim()),StringUtil.toInt(pattern,slash+1));

            if (dash>=0)
                return new MinMaxInetRange(pattern,InetAddress.getByName(pattern.substring(0,dash).trim()),InetAddress.getByName(pattern.substring(dash+1).trim()));
            
            return new SingletonInetRange(pattern,InetAddress.getByName(pattern));
        }
        catch(Exception e)
        {
            try
            {
                if (slash<0 && dash>0)
                    return new LegacyInetRange(pattern);
            }
            catch(Exception e2)
            {
                e.addSuppressed(e2);
            }
            throw new IllegalArgumentException("Bad pattern: "+pattern,e);
        }
    }
