    public Object getValue(EvaluationContext ctx)
            throws ELException {
        StringBuffer sb = new StringBuffer(16);
        Object obj = null;
        if (this.children != null) {
            for (int i = 0; i < this.children.length; i++) {
                obj = this.children[i].getValue(ctx);
                if (obj != null) {
                    sb.append(obj);
                }
            }
        }
        return sb.toString();
    }
