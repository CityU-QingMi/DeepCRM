        @Override
        public void run() {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(is));
                String line;

                while ((line = br.readLine()) != null) {
                    if(type.equalsIgnoreCase("stderr")) {
                        getLog().warn(type + ">" + line);
                    } else {
                        getLog().info(type + ">" + line);
                    }
                }
            } catch (IOException ioe) {
                getLog().error("Error consuming " + type + " stream of spawned process.", ioe);
            } finally {
                if(br != null) {
                    try { br.close(); } catch(Exception ignore) {}
                }
            }
        }
