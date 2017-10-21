    public static void main(String[] args)
    {
        System.out.printf("PropertyDump%n");
        // As System Properties
        Properties props = System.getProperties();
        Enumeration<?> names = props.propertyNames();
        while (names.hasMoreElements())
        {
            String name = (String)names.nextElement();
            // only interested in "test." prefixed properties
            if (name.startsWith("test."))
            {
                System.out.printf("System %s=%s%n",name,props.getProperty(name));
            }
        }

        // As File Argument
        for (String arg : args)
        {
            if (arg.endsWith(".properties"))
            {
                Properties aprops = new Properties();
                File propFile = new File(arg);
                try (FileReader reader = new FileReader(propFile))
                {
                    aprops.load(reader);
                    Enumeration<?> anames = aprops.propertyNames();
                    while (anames.hasMoreElements())
                    {
                        String name = (String)anames.nextElement();
                        if (name.startsWith("test."))
                        {
                            System.out.printf("%s %s=%s%n",propFile.getName(),name,aprops.getProperty(name));
                        }
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        System.exit(0);
    }
