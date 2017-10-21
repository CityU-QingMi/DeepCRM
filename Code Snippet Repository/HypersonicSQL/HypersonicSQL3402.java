    public TestScriptRunner(String rcFileString, Map scriptFileMap)
            throws IOException {
        TestUtil.setAbortOnErr(true);
        Map rcdataMap = new HashMap();
        File rcFile = new File(rcFileString);
        if (!rcFile.isFile())
            throw new IllegalArgumentException(
                    "RC file '" + rcFileString + "' not a file");

        String scriptPath, urlid;
        Iterator it;
        File file;
        Reader reader = null;

        it = scriptFileMap.values().iterator();
        while (it.hasNext()) {
            urlid = (String) it.next();
            if (rcdataMap.containsKey(urlid)) continue;
            try {
                rcdataMap.put(urlid, new RCData(rcFile, urlid));
            } catch (Exception e) {
                throw new RuntimeException(
                        "Failed to instantiate RCData with file '"
                        + rcFile + "' for urlid '" + urlid + "'", e);
            }
        }

        it = scriptFileMap.keySet().iterator();
        while (it.hasNext()) {
            scriptPath = (String) it.next();
            urlid = (String) scriptFileMap.get(scriptPath);

            if (scriptPath.equals("-")) {
                reader = new InputStreamReader(System.in);
            } else {
                file = new File(scriptPath);
                if (!file.isFile()) throw new IOException("'" + file
                        + "' is not a file");
                if (!file.canRead()) throw new IOException("'" + file
                        + "' is not readable");
                reader = new FileReader(file);
            }
            scriptRuns.add(new ScriptRun(scriptPath,
                    reader, (RCData) rcdataMap.get(urlid)));
        }
    }
