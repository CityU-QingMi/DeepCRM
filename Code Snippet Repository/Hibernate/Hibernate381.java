    protected Properties properties() {
        Properties properties = new Properties( );
        URL propertiesURL = Thread.currentThread().getContextClassLoader().getResource( "hibernate.properties" );
        try(FileInputStream inputStream = new FileInputStream( propertiesURL.getFile() )) {
            properties.load( inputStream );
        }
        catch (IOException e) {
            throw new IllegalArgumentException( e );
        }
        return properties;
    }
