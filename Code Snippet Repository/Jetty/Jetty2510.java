    public static void main(String... args) throws Exception
    {
        Resource war = null;
        Resource dir = null;
        Resource xml = null;

        switch (args.length)
        {
            case 0:
                error("No WAR file or directory given");
                break;

            case 1:
                dir = Resource.newResource(args[0]);
                break;

            case 2:
                war = Resource.newResource(args[0]);
                if (war.isDirectory())
                {
                    dir = war;
                    war = null;
                    xml = Resource.newResource(args[1]);
                }
                else
                {
                    dir = Resource.newResource(args[1]);
                }

                break;

            case 3:
                war = Resource.newResource(args[0]);
                dir = Resource.newResource(args[1]);
                xml = Resource.newResource(args[2]);
                break;

            default:
                error("Too many args");
                break;
        }

        
        preconfigure(war,dir,xml);
    }
