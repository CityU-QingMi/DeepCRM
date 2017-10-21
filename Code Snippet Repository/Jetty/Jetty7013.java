    public void copyWebInf(String testResourceName) throws IOException
    {
        webinf = new File(contextDir,"WEB-INF");
        FS.ensureDirExists(webinf);
        classesDir = new File(webinf,"classes");
        FS.ensureDirExists(classesDir);
        File webxml = new File(webinf,"web.xml");
        File testWebXml = MavenTestingUtils.getTestResourceFile(testResourceName);
        IO.copy(testWebXml,webxml);
    }
