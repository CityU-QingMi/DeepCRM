    public void testWebFlow() throws Exception {
        // use the classloader rather than relying on the
        // working directory being an assumed value when
        // running the test:  so let's get this class's parent dir
        URL url = ClassLoaderUtil.getResource("org/apache/struts2/sitegraph/struts.xml", SiteGraphTest.class);
        File file = new File(url.toURI());
        String dir = file.getParent();

        SiteGraph siteGraph = new SiteGraph(dir, dir, dir, "");
        StringWriter writer = new StringWriter();
        siteGraph.setWriter(writer);
        siteGraph.prepare();

        URL compare = SiteGraphTest.class.getResource("out.txt");
        StringBuilder buffer = new StringBuilder(128);
        try (InputStream in = compare.openStream()){
	        byte[] buf = new byte[4096];
	        int nbytes;
	
	        while ((nbytes = in.read(buf)) > 0) {
	            buffer.append(new String(buf, 0, nbytes));
	        }
        }
        assertEquals(buffer.toString().replaceAll("\r\n", "\n"), writer.toString().replaceAll("\r\n", "\n"));
    }
