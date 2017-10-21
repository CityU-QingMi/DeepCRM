        @Override
        public void run()
        {
            File buildFile = new File(_ant);
            
            Project antProject = new Project();
            try
            {
                antProject.setBaseDir(MavenTestingUtils.getBaseDir());
                antProject.setUserProperty("ant.file",buildFile.getAbsolutePath());
                DefaultLogger logger = new DefaultLogger();

                ConsoleParser parser = new ConsoleParser();
                //connList = parser.newPattern(".*([0-9]+\\.[0-9]*\\.[0-9]*\\.[0-9]*):([0-9]*)",1);
                connList = parser.newPattern("Jetty AntTask Started",1);

                PipedOutputStream pos = new PipedOutputStream();
                PipedInputStream pis = new PipedInputStream(pos);

                PipedOutputStream pose = new PipedOutputStream();
                PipedInputStream pise = new PipedInputStream(pose);

                startPump("STDOUT",parser,pis);
                startPump("STDERR",parser,pise);
                
                logger.setErrorPrintStream(new PrintStream(pos));
                logger.setOutputPrintStream(new PrintStream(pose));
                logger.setMessageOutputLevel(Project.MSG_VERBOSE);
                antProject.addBuildListener(logger);

                antProject.fireBuildStarted();
                antProject.init();

                ProjectHelper helper = ProjectHelper.getProjectHelper();

                antProject.addReference("ant.projectHelper",helper);

                helper.parse(antProject,buildFile);

                antProject.executeTarget("jetty.run");
            
                parser.waitForDone(10000,TimeUnit.MILLISECONDS);
            }
            catch (Exception e)
            {
                antProject.fireBuildFinished(e);
            }
        }
