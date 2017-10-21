    private String initUsersPackedFileText()
        throws Exception
    {
        Path dir = testdir.getPath().toRealPath();
        FS.ensureDirExists( dir.toFile() );
        File users = dir.resolve( "users.txt" ).toFile();
        writeUser( users );
        File usersJar = dir.resolve( "users.jar" ).toFile();
        String entryPath = "mountain_goat/pale_ale.txt";
        try (FileInputStream fileInputStream = new FileInputStream( users ))
        {
            try (OutputStream outputStream = new FileOutputStream( usersJar ))
            {
                try (JarOutputStream jarOutputStream = new JarOutputStream( outputStream ))
                {
                    // add fake entry
                    jarOutputStream.putNextEntry( new JarEntry( "foo/wine" ) );

                    JarEntry jarEntry = new JarEntry( entryPath );
                    jarOutputStream.putNextEntry( jarEntry );
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ( ( bytesRead = fileInputStream.read( buffer ) ) != -1 )
                    {
                        jarOutputStream.write( buffer, 0, bytesRead );
                    }
                    // add fake entry
                    jarOutputStream.putNextEntry( new JarEntry( "foo/cheese" ) );
                }
            }

        }
        return "jar:" + usersJar.toURI().toASCIIString() + "!/" + entryPath;
    }
