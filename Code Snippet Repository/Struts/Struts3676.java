    public static void main(String[] args) throws IOException {
        if (LOG.isInfoEnabled()) {
            LOG.info("SiteGraph starting...");
        }

        if (args.length != 8 && args.length != 6) {
            try (InputStream is = SiteGraph.class.getResourceAsStream("sitegraph-usage.txt");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[2048];
                int length;
                while ((length = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, length);
                }
            
                String usage = baos.toString();
                System.out.println(usage.replaceAll("//.*", ""));
            }
            return;
        }

        String configDir = getArg(args, "config");
        String views = getArg(args, "views");
        String output = getArg(args, "output");
        String namespace = getArg(args, "ns");

        /** <!-- START SNIPPET: example-api --> */
        SiteGraph siteGraph = new SiteGraph(configDir, views, output, namespace);
        siteGraph.prepare();
        siteGraph.render();
        /** <!-- END SNIPPET: example-api --> */
    }
