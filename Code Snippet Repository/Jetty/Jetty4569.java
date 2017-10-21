    public void dumpProperties()
    {
        System.out.println();
        System.out.println("Properties:");
        System.out.println("-----------");

        List<String> sortedKeys = new ArrayList<>();
        for (Prop prop : properties)
        {
            if (prop.origin.equals(Props.ORIGIN_SYSPROP))
            {
                continue; // skip
            }
            sortedKeys.add(prop.key);
        }

        if (sortedKeys.isEmpty())
        {
            System.out.println(" (no properties specified)");
            return;
        }

        Collections.sort(sortedKeys);

        for (String key : sortedKeys)
        {
            dumpProperty(key);
        }
    }
