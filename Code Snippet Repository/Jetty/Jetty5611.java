    @Test
    public void testRollover() throws Exception
    {
        File testDir = MavenTestingUtils.getTargetTestingDir(RolloverFileOutputStreamTest.class.getName() + "_testRollover");
        FS.ensureEmpty(testDir);

        ZoneId zone = toZoneId("Australia/Sydney");
        ZonedDateTime now = toDateTime("2016.04.10-11:59:55.0 PM AEDT", zone);
        
        File template = new File(testDir,"test-rofos-yyyy_mm_dd.log");
        
        try (RolloverFileOutputStream rofos = 
            new RolloverFileOutputStream(template.getAbsolutePath(),false,0,TimeZone.getTimeZone(zone),null,null,now))
        {
            rofos.write("BEFORE".getBytes());
            rofos.flush();
            String[] ls = testDir.list();
            assertThat(ls.length,is(1));
            assertThat(ls[0],is("test-rofos-2016_04_10.log"));

            TimeUnit.SECONDS.sleep(10);
            rofos.write("AFTER".getBytes());
            ls = testDir.list();
            assertThat(ls.length,is(2));
            
            for (String n : ls)
            {
                String content = IO.toString(new FileReader(new File(testDir,n)));
                if ("test-rofos-2016_04_10.log".equals(n))
                {
                    assertThat(content,is("BEFORE"));
                }
                else
                {
                    assertThat(content,is("AFTER"));
                }
            }
        }
    }
