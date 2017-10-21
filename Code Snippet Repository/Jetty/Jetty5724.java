    @Test
    public void testNamedLogging()
    {
        Red red = new Red();
        Green green = new Green();
        Blue blue = new Blue();

        StdErrCapture output = new StdErrCapture();

        setLoggerOptions(Red.class,output);
        setLoggerOptions(Green.class,output);
        setLoggerOptions(Blue.class,output);

        red.generateLogs();
        green.generateLogs();
        blue.generateLogs();
        
        output.assertContains(Red.class.getName());
        output.assertContains(Green.class.getName());
        output.assertContains(Blue.class.getName());
    }
