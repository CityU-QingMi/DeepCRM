    @Test
    public void gregorianCalendarIsUsed()
    {
        String dateBefore = gregorianDate();

        RemoteSnapshotMetadata metadata = new RemoteSnapshotMetadata(
                new DefaultArtifact( "a:b:1-SNAPSHOT" ), false);
        metadata.merge( new Metadata() );

        String dateAfter = gregorianDate();

        String ts = metadata.metadata.getVersioning().getSnapshot().getTimestamp();
        String datePart = ts.replaceAll( "\\..*", "" );

        /* Allow for this test running across midnight */
        Set<String> expected = new HashSet<String>( Arrays.asList( dateBefore, dateAfter ) );
        assertTrue( "Expected " + datePart + " to be in " + expected,
                expected.contains( datePart ) );
    }
