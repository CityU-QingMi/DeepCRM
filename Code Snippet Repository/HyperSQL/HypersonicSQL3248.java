    private boolean equalsVector(HsqlList list, Vector vector) {

        if (list.size() != vector.size()) {
            return false;
        }

        Iterator listElements   = list.iterator();
        Enumeration             vectorElements = vector.elements();
        Object                  listObj        = null;
        Object                  vectorObj      = null;

        while (listElements.hasNext()) {
            listObj   = listElements.next();
            vectorObj = vectorElements.nextElement();

            if (!listObj.equals(vectorObj)) {
                return false;
            }
        }

        return true;
    }
