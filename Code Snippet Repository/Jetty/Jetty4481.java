    private FileArg(final String moduleName, final String uriLocation)
    {
        this.moduleName = moduleName;
        String parts[] = uriLocation.split("\\|",3);
        if (parts.length > 2)
        {
            StringBuilder err = new StringBuilder();
            final String LN = System.lineSeparator();
            err.append("Unrecognized [file] argument: ").append(uriLocation);
            err.append(LN).append("Valid Syntaxes: ");
            err.append(LN).append("    <relative-path>       - eg: resources/");
            err.append(LN).append(" or <absolute-path>       - eg: /var/run/jetty.pid");
            err.append(LN).append(" or <uri>                 - eg: basehome:some/path");
            err.append(LN).append(" or <uri>|<rel-path>      - eg: http://machine/my.conf|resources/my.conf");
            err.append(LN).append(" or <uri>|<abs-path>      - eg: http://machine/glob.dat|/opt/run/glob.dat");
            err.append(LN).append("Known uri schemes: http, maven, home");
            throw new IllegalArgumentException(err.toString());
        }
        if (parts.length == 2)
        {
            this.uri = parts[0];
            this.location = parts[1];
        }
        else if (uriLocation.contains(":"))
        {
            this.uri = uriLocation;
            this.location = null;
        }
        else
        {
            this.uri = null;
            this.location = uriLocation;
        }
    }
