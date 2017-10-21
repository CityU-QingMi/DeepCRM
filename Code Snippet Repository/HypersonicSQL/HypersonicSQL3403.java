    public boolean runScripts() {
        ScriptRun scriptRun;
        for (int i = 0; i < scriptRuns.size(); i++) {
            scriptRun = (ScriptRun) scriptRuns.get(i);
            if (verbose) System.out.print("Starting " + (++i) + " / "
                + scriptRuns.size() + "...");
            scriptRun.start();
            if (verbose) System.out.println("  +");
            if (!threaded) try {
                scriptRun.join();
            } catch (InterruptedException ie) {
                throw new RuntimeException(
                        "Interrupted while waiting for script '"
                        + scriptRun.getName() + "' to execute", ie);
            }
        }
        if (threaded) {
            if (verbose)
                System.out.println(
                        "All scripts started.  Will now wait for them.");
            for (int i = 0; i < scriptRuns.size(); i++) try {
                ((ScriptRun) scriptRuns.get(i)).join();
            } catch (InterruptedException ie) {
                throw new RuntimeException(
                        "Interrupted while waiting for script to execute", ie);
            }
        }
        for (int i = 0; i < scriptRuns.size(); i++) {
            if (!((ScriptRun) scriptRuns.get(i)).getSuccess()) return false;
        }
        return true;
    }
