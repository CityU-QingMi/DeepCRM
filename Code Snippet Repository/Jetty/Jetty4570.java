    private void dumpProperty(String key)
    {
        Prop prop = properties.getProp(key);
        if (prop == null)
        {
            System.out.printf(" %s (not defined)%n",key);
        }
        else
        {
            System.out.printf(" %s = %s%n",key,prop.value);
            if (StartLog.isDebugEnabled())
            {
                System.out.printf("   origin: %s%n",prop.origin);
                while (prop.overrides != null)
                {
                    prop = prop.overrides;
                    System.out.printf("   (overrides)%n");
                    System.out.printf("     %s = %s%n",key,prop.value);
                    System.out.printf("     origin: %s%n",prop.origin);
                }
            }
        }
    }
