    public synchronized void init(Weblog website) throws WebloggerException {
        // don't do this work if Smileys already loaded
        if (SmileysPlugin.smileyPatterns.length < 1) {
            String baseURL = WebloggerRuntimeConfig.getAbsoluteContextURL();
            
            Pattern[] tempP = new Pattern[SmileysPlugin.smileyDefs.size()];
            String[] tempS = new String[SmileysPlugin.smileyDefs.size()];
            log.debug("# smileys: " + smileyDefs.size());
            int count = 0;
            Enumeration enum1 = SmileysPlugin.smileyDefs.propertyNames();
            while(enum1.hasMoreElements()) {
                String smiley = (String)enum1.nextElement();
                String smileyAlt = htmlEscape(smiley);
                tempP[count] = Pattern.compile(regexEscape(smiley));
                tempS[count] = "<img src=\"" +
                        baseURL + "/images/smileys/" +
                        smileyDefs.getProperty(smiley, "smile.gif") +
                        "\" class=\"smiley\"" +
                        " alt=\"" + smileyAlt + "\"" +
                        " title=\"" + smileyAlt +"\" />";
                log.debug(smiley + "=" + tempS[count]);
                count++;
            }
            SmileysPlugin.smileyPatterns = tempP;
            SmileysPlugin.imageTags = tempS;
        }
    }
