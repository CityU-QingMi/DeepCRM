    public Slf4jLog(String name)
    {
        //NOTE: if only an slf4j-api jar is on the classpath, slf4j will use a NOPLogger
        org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger( name );
        
        // Fix LocationAwareLogger use to indicate FQCN of this class - 
        // https://bugs.eclipse.org/bugs/show_bug.cgi?id=276670
        if (logger instanceof org.slf4j.spi.LocationAwareLogger)
        {
            _logger = new JettyAwareLogger((org.slf4j.spi.LocationAwareLogger)logger);
        }
        else
        {
            _logger = logger;
        }
    }
