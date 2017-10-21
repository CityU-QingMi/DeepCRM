    public URL getResource(String name) {

        URL result = null;

        if (bestCandidate != null) {
            result = bestCandidate.getResource(name);
            if(result == null) {
              bestCandidate = null;
            }
            else {
                return result;
            }
        }

        ClassLoadHelper loadHelper = null;

        Iterator<ClassLoadHelper> iter = loadHelpers.iterator();
        while (iter.hasNext()) {
            loadHelper = iter.next();

            result = loadHelper.getResource(name);
            if (result != null) {
                break;
            }
        }

        bestCandidate = loadHelper;
        return result;
    }
