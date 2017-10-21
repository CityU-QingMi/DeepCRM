    public void dumpJvmArgs()
    {
        System.out.println();
        System.out.println("JVM Arguments:");
        System.out.println("--------------");
        if (jvmArgs.isEmpty())
        {
            System.out.println(" (no jvm args specified)");
            return;
        }

        for (String jvmArgKey : jvmArgs)
        {
            String value = System.getProperty(jvmArgKey);
            if (value != null)
            {
                System.out.printf(" %s = %s%n",jvmArgKey,value);
            }
            else
            {
                System.out.printf(" %s%n",jvmArgKey);
            }
        }
    }
