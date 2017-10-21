    public static Password getPassword(String realm, String dft, String promptDft)
    {
        String passwd = System.getProperty(realm, dft);
        if (passwd == null || passwd.length() == 0)
        {
            try
            {
                System.out.print(realm + ((promptDft != null && promptDft.length() > 0) ? " [dft]" : "") + " : ");
                System.out.flush();
                byte[] buf = new byte[512];
                int len = System.in.read(buf);
                if (len > 0) passwd = new String(buf, 0, len).trim();
            }
            catch (IOException e)
            {
                LOG.warn(Log.EXCEPTION, e);
            }
            if (passwd == null || passwd.length() == 0) passwd = promptDft;
        }
        return new Password(passwd);
    }
