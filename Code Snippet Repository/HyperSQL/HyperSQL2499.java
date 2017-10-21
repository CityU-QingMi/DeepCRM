    private boolean objectEquals(Object lObject, Object aObject,
                                 Object vObject) {

        if (lObject == null && aObject == null && vObject == null) {
            return true;
        }

        try {
            if (!lObject.equals(vObject)) {
                System.out.println("LinkList object returned inconsistent");

                return false;
            } else if (!aObject.equals(vObject)) {
                System.out.println("ArrayList object returned inconsistent");

                return false;
            } else {
                return true;
            }
        } catch (NullPointerException ex) {
            return false;
        }
    }
