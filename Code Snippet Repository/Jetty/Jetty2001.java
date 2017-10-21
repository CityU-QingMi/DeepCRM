    public void getConfiguration (String[] args)
    throws Exception
    {
        for (int i=0; i<args.length; i++)
        {
            //--stop-port
            if ("--stop-port".equals(args[i]))
                stopPort = Integer.parseInt(args[++i]);

            //--stop-key
            if ("--stop-key".equals(args[i]))
                stopKey = args[++i];

            //--jettyXml
            if ("--jetty-xml".equals(args[i]))
            {
                jettyXmls = new ArrayList<File>();
                String[] names = StringUtil.csvSplit(args[++i]);
                for (int j=0; names!= null && j < names.length; j++)
                {
                    jettyXmls.add(new File(names[j].trim()));
                }  
            }

            //--context-xml
            if ("--context-xml".equals(args[i]))
            {
                contextXml = new File(args[++i]);
            }

            //--props
            if ("--props".equals(args[i]))
            {
                File f = new File(args[++i].trim());
                props = new Properties();
                try (InputStream in = new FileInputStream(f))
                {
                    props.load(in);
                }
            }
            
            //--token
            if ("--token".equals(args[i]))
            {
                token = args[++i].trim();
            }
        }
    }
