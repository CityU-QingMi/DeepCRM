    public static String showVersion()
    {
        final String ls = System.getProperty( "line.separator" );
        Properties properties = getBuildProperties();
        StringBuilder version = new StringBuilder( 256 );
        version.append( buffer().strong( createMavenVersionString( properties ) ) ).append( ls );
        version.append( reduce(
            properties.getProperty( "distributionShortName" ) + " home: " + System.getProperty( "maven.home",
                                                                                                "<unknown Maven "
                                                                                                    + "home>" ) ) )
            .append(
            ls );
        version.append( "Java version: " ).append(
            System.getProperty( "java.version", "<unknown Java version>" ) ).append( ", vendor: " ).append(
            System.getProperty( "java.vendor", "<unknown vendor>" ) ).append( ls );
        version.append( "Java home: " ).append( System.getProperty( "java.home", "<unknown Java home>" ) ).append( ls );
        version.append( "Default locale: " ).append( Locale.getDefault() ).append( ", platform encoding: " ).append(
            System.getProperty( "file.encoding", "<unknown encoding>" ) ).append( ls );
        version.append( "OS name: \"" ).append( Os.OS_NAME ).append( "\", version: \"" ).append( Os.OS_VERSION ).append(
            "\", arch: \"" ).append( Os.OS_ARCH ).append( "\", family: \"" ).append( Os.OS_FAMILY ).append( '\"' );
        return version.toString();
    }
