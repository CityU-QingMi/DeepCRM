    public SQLScriptRunner(InputStream is) throws IOException {
        
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String command = ""; 
        String line;
        while ((line = in.readLine()) != null) {
            line = line.trim();

            // ignore lines starting with "--"
            if (!line.startsWith("--")) {
                
                if (line.indexOf("--") > 0) {
                    // trim comment off end of line
                    line = line.substring(0, line.indexOf("--")).trim();
                }
                
                // add line to current command
                command += line.trim();
                if (command.endsWith(";")) { 
                    // ";" is end of command, so add completed command to list
                    String cmd = command.substring(0, command.length() - 1);
                    String[] cmdArray = StringUtils.split(cmd);
                    cmd = StringUtils.join(cmdArray, " ");
                    commands.add(cmd);
                    command = "";
                } else if (StringUtils.isNotEmpty(command)) {
                    // still more command coming so add space
                    command += " ";
                }
            } 
        }
        in.close();    
    }
