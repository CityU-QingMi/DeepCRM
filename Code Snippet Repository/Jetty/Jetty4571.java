    public void dumpSystemProperties()
    {
        System.out.println();
        System.out.println("System Properties:");
        System.out.println("------------------");

        if (systemPropertyKeys.isEmpty())
        {
            System.out.println(" (no system properties specified)");
            return;
        }

        List<String> sortedKeys = new ArrayList<>();
        sortedKeys.addAll(systemPropertyKeys);
        Collections.sort(sortedKeys);

        for (String key : sortedKeys)
        {
            String value = System.getProperty(key);
            System.out.printf(" %s = %s%n",key,value);
        }
    }
