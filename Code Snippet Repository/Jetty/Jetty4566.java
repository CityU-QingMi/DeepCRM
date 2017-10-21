    public void dumpActiveXmls()
    {
        System.out.println();
        System.out.println("Jetty Active XMLs:");
        System.out.println("------------------");
        if (xmls.isEmpty())
        {
            System.out.println(" (no xml files specified)");
            return;
        }

        for (Path xml : xmls)
        {
            System.out.printf(" %s%n",baseHome.toShortForm(xml.toAbsolutePath()));
        }
    }
