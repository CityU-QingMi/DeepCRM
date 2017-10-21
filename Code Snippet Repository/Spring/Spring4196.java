    void visitSubroutine(final Label JSR, final long id, final int nbSubroutines) {
        // user managed stack of labels, to avoid using a recursive method
        // (recursivity can lead to stack overflow with very large methods)
        Label stack = this;
        while (stack != null) {
            // removes a label l from the stack
            Label l = stack;
            stack = l.next;
            l.next = null;

            if (JSR != null) {
                if ((l.status & VISITED2) != 0) {
                    continue;
                }
                l.status |= VISITED2;
                // adds JSR to the successors of l, if it is a RET block
                if ((l.status & RET) != 0) {
                    if (!l.inSameSubroutine(JSR)) {
                        Edge e = new Edge();
                        e.info = l.inputStackTop;
                        e.successor = JSR.successors.successor;
                        e.next = l.successors;
                        l.successors = e;
                    }
                }
            } else {
                // if the l block already belongs to subroutine 'id', continue
                if (l.inSubroutine(id)) {
                    continue;
                }
                // marks the l block as belonging to subroutine 'id'
                l.addToSubroutine(id, nbSubroutines);
            }
            // pushes each successor of l on the stack, except JSR targets
            Edge e = l.successors;
            while (e != null) {
                // if the l block is a JSR block, then 'l.successors.next' leads
                // to the JSR target (see {@link #visitJumpInsn}) and must
                // therefore not be followed
                if ((l.status & Label.JSR) == 0 || e != l.successors.next) {
                    // pushes e.successor on the stack if it not already added
                    if (e.successor.next == null) {
                        e.successor.next = stack;
                        stack = e.successor;
                    }
                }
                e = e.next;
            }
        }
    }
