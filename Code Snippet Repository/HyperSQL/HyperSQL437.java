    int checkNodes(PersistentStore store, NodeAVL p) {

        NodeAVL l      = p.nLeft;
        NodeAVL r      = p.nRight;
        int     errors = 0;

        if (l != null && l.getBalance(store) == -2) {
            System.out.print("broken index - deleted");

            errors++;
        }

        if (r != null && r.getBalance(store) == -2) {
            System.out.print("broken index -deleted");

            errors++;
        }

        if (l != null && !p.equals(l.getParent(store))) {
            System.out.print("broken index - no parent");

            errors++;
        }

        if (r != null && !p.equals(r.getParent(store))) {
            System.out.print("broken index - no parent");

            errors++;
        }

        return errors;
    }
