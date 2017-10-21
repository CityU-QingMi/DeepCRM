    public static synchronized void clearLoggers(String prefixToZap) {

        Set                targetKeys = new HashSet();
        java.util.Iterator it         = loggerInstances.keySet().iterator();
        String             k;
        String             dottedPrefix = prefixToZap + '.';

        while (it.hasNext()) {
            k = (String) it.next();

            if (k.equals(prefixToZap) || k.startsWith(dottedPrefix)) {
                targetKeys.add(k);
            }
        }

        loggerInstances.keySet().removeAll(targetKeys);
    }
