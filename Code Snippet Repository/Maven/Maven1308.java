    private CommandLine cliMerge( CommandLine mavenArgs, CommandLine mavenConfig )
    {
        CommandLine.Builder commandLineBuilder = new CommandLine.Builder();
        
        // the args are easy, cli first then config file
        for ( String arg : mavenArgs.getArgs() )
        {
            commandLineBuilder.addArg( arg );
        }
        for ( String arg : mavenConfig.getArgs() )
        {
            commandLineBuilder.addArg( arg );
        }
        
        // now add all options, except for -D with cli first then config file
        List<Option> setPropertyOptions = new ArrayList<>();
        for ( Option opt : mavenArgs.getOptions() )
        {
            if ( String.valueOf( CLIManager.SET_SYSTEM_PROPERTY ).equals( opt.getOpt() ) )
            {
                setPropertyOptions.add( opt );
            }
            else
            {
                commandLineBuilder.addOption( opt );
            }
        }
        for ( Option opt : mavenConfig.getOptions() )
        {
            commandLineBuilder.addOption( opt );
        }
        // finally add the CLI system properties
        for ( Option opt : setPropertyOptions )
        {
            commandLineBuilder.addOption( opt );
        }
        return commandLineBuilder.build();
    }
