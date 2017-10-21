    public static void main(String[] args)
    {
        Runner runner = new Runner();

        try
        {
            if (args.length>0&&args[0].equalsIgnoreCase("--help"))
            {
                runner.usage(null);
            }
            else if (args.length>0&&args[0].equalsIgnoreCase("--version"))
            {
                runner.version();
            }
            else
            {
                runner.configure(args);
                runner.run();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            runner.usage(null);
        }
    }
