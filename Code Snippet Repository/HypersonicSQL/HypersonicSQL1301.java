    public Calculator(String[] sa, Map<String, String> vars) {
/**/
/**/
        if (vars.size() < 1)
            throw new IllegalArgumentException("No expression supplied");
        this.vars = vars;
        Atom atom = null, prePrevAtom;
        int prevIndex;
        NEXT_TOKEN:
        for (String token : sa) try {
            atom = new Atom(token);
            prevIndex = atoms.size() - 1;
            if (prevIndex < 0) continue;
            if (atoms.get(prevIndex).op != MathOp.SUBTRACT) continue;
            prePrevAtom = (prevIndex > 0) ? atoms.get(prevIndex-1) : null;
            if (prePrevAtom != null && !TradOrLParen.contains(prePrevAtom.op))
                continue;

            if (atom.op == null) {
                atoms.remove(prevIndex);
                atom.val *= -1;
            } else if (atom.op == MathOp.LPAREN) {
                atoms.remove(prevIndex);
                atoms.add(new Atom(-1L));
                atoms.add(new Atom(MathOp.MULTIPLY));
            }
        } finally {
            atoms.add(atom);
        }
    }
